package ifpb.bancoDeDados.BancodeDados.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalization;
import ifpb.bancoDeDados.BancodeDados.service.validation.ValidatorEngine;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.FileReader;
import java.sql.*;
import java.util.*;

@Service
public class CsvImportJdbcService {
    private final DataSource dataSource;
    private final ValidatorEngine engine;
    private final UFService ufService; // usado apenas se quiser sincronizar/consultar via JPA (opcional)
    private final MunicipioService municipioService; // opcional
    private final CategoriaAnimalService categoriaService; // opcional

    // caches locais para evitar SELECTs repetidos: chave -> id
    private final Map<String, Long> ufCache = new HashMap<>(); // key = sigla
    private final Map<String, Long> municipioCache = new HashMap<>(); // key = sigla + "|" + municipioNome
    private final Map<String, Long> categoriaCache = new HashMap<>(); // key = categoriaNomeNormalized

    // Configurável
    private final int batchSize = 2000;


    public CsvImportJdbcService(
            DataSource dataSource,
            ValidatorEngine engine,
            UFService ufService,
            MunicipioService municipioService,
            CategoriaAnimalService categoriaService
    ) {
        this.dataSource = dataSource;
        this.engine = engine;
        this.ufService = ufService;
        this.municipioService = municipioService;
        this.categoriaService = categoriaService;
    }

    /**
     * Importa o CSV de forma rápida via JDBC + batch.
     *
     * @param caminhoCsv caminho do arquivo CSV (pode usar classpath ou caminho absoluto)
     * @return resumo com contadores (linhas lidas, inseridas, ignoradas, erros)
     * @throws Exception em caso de falha irreversível
     */
    public Map<String, Object> importarCsvJdbc(String caminhoCsv) throws Exception {
        int lidas = 0;
        int inseridas = 0;
        int ignoradas = 0;
        int erros = 0;

        // SQL para inserir abate (usa subselects para pegar FK)
        String insertAbateSql = "INSERT INTO abate (ano, mes, quantidade, municipio_id, categoria_animal_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false); // controlamos o commit por batch

            try (CSVReader reader = new CSVReaderBuilder(new FileReader(caminhoCsv))
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .build();
                 PreparedStatement psInsertAbate = conn.prepareStatement(insertAbateSql)
            ) {
                // pular header
                String[] linha = reader.readNext();

                int batchCount = 0;
                while ((linha = reader.readNext()) != null) {
                    lidas++;
                    try {
                        // 1) valida a linha com seu ValidatorEngine
                        engine.validate(linha);

                        // 2) parser seguro
                        int ano = Integer.parseInt(linha[0].trim());
                        int mes = Integer.parseInt(linha[1].trim());
                        String siglaUFRaw = linha[2].trim();
                        String nomeMunicipioRaw = linha[3].trim();
                        String categoriaRaw = linha[4].trim();
                        long quantidade = Long.parseLong(linha[5].trim());

                        // 3) normaliza a categoria usando seu SPI loader
                        String categoriaNormalizada = CategoriaNormalization.normalizar(categoriaRaw);

                        // 4) resolve/insere UF, Município, Categoria e obtém ids (com cache)
                        long ufId = getOrCreateUf(conn, siglaUFRaw);
                        long municipioId = getOrCreateMunicipio(conn, nomeMunicipioRaw, ufId, siglaUFRaw);
                        long categoriaId = getOrCreateCategoria(conn, categoriaNormalizada);

                        if (municipioId <= 0 || categoriaId <= 0) {
                            // erro para essa linha (não insere)
                            ignoradas++;
                            continue;
                        }

                        // 5) adiciona ao batch de abate
                        psInsertAbate.setInt(1, ano);
                        psInsertAbate.setInt(2, mes);
                        psInsertAbate.setLong(3, quantidade);
                        psInsertAbate.setLong(4, municipioId);
                        psInsertAbate.setLong(5, categoriaId);
                        psInsertAbate.addBatch();

                        batchCount++;

                        if (batchCount % batchSize == 0) {
                            int[] results = psInsertAbate.executeBatch();
                            conn.commit();
                            inseridas += Arrays.stream(results).sum(); // note: para drivers pode retornar -2 for SUCCESS_NO_INFO
                            batchCount = 0;
                        }

                    } catch (Exception eRow) {
                        // não interrompe toda a importação — só conta e loga
                        erros++;
                        System.out.println("⚠ Erro na linha " + lidas + ": " + eRow.getMessage());
                    }
                } // fim while

                // executa qualquer restante
                int[] rest = psInsertAbate.executeBatch();
                conn.commit();
                // somando resultados restantes
                for (int r : rest) {
                    if (r >= 0) inseridas += r;
                    else if (r == Statement.SUCCESS_NO_INFO) inseridas += 1; // aproximação
                }
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }

        Map<String, Object> resumo = new HashMap<>();
        resumo.put("lidas", lidas);
        resumo.put("inseridas (estimadas)", inseridas);
        resumo.put("ignoradas", ignoradas);
        resumo.put("erros", erros);
        return resumo;
    }

    // -------------------------
    // Métodos auxiliares (get-or-create) com cache
    // -------------------------

    private long getOrCreateUf(Connection conn, String sigla) throws SQLException {
        if (sigla == null) return -1;
        sigla = sigla.trim().toUpperCase();

        // cache
        if (ufCache.containsKey(sigla)) return ufCache.get(sigla);

        // 1) SELECT id
        String sel = "SELECT id FROM uf WHERE sigla = ?";
        try (PreparedStatement ps = conn.prepareStatement(sel)) {
            ps.setString(1, sigla);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    ufCache.put(sigla, id);
                    return id;
                }
            }
        }

        // 2) INSERT (nome = sigla se nome não estiver disponível)
        String ins = "INSERT INTO uf (sigla, nome) VALUES (?, ?)";
        try (PreparedStatement psIns = conn.prepareStatement(ins, Statement.RETURN_GENERATED_KEYS)) {
            psIns.setString(1, sigla);
            psIns.setString(2, sigla); // fallback nome = sigla; se você tiver um map de sigla->nome, substitua aqui
            psIns.executeUpdate();
            try (ResultSet gk = psIns.getGeneratedKeys()) {
                if (gk.next()) {
                    long id = gk.getLong(1);
                    ufCache.put(sigla, id);
                    return id;
                }
            }
        }

        // fallback: tentar select novamente (em casos de concorrência)
        try (PreparedStatement ps = conn.prepareStatement(sel)) {
            ps.setString(1, sigla);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    ufCache.put(sigla, id);
                    return id;
                }
            }
        }

        return -1;
    }

    private long getOrCreateMunicipio(Connection conn, String municipioNome, long ufId, String ufSigla) throws SQLException {
        if (municipioNome == null) return -1;
        municipioNome = municipioNome.trim();

        String cacheKey = ufSigla.trim().toUpperCase() + "|" + municipioNome.toLowerCase();
        if (municipioCache.containsKey(cacheKey)) return municipioCache.get(cacheKey);

        // 1) SELECT com join uf
        String sel = "SELECT m.id FROM municipio m JOIN uf u ON m.uf_id = u.id WHERE m.nome = ? AND u.id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sel)) {
            ps.setString(1, municipioNome);
            ps.setLong(2, ufId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    municipioCache.put(cacheKey, id);
                    return id;
                }
            }
        }

        // 2) INSERT
        String ins = "INSERT INTO municipio (nome, uf_id) VALUES (?, ?)";
        try (PreparedStatement psIns = conn.prepareStatement(ins, Statement.RETURN_GENERATED_KEYS)) {
            psIns.setString(1, municipioNome);
            psIns.setLong(2, ufId);
            psIns.executeUpdate();
            try (ResultSet gk = psIns.getGeneratedKeys()) {
                if (gk.next()) {
                    long id = gk.getLong(1);
                    municipioCache.put(cacheKey, id);
                    return id;
                }
            }
        }

        // fallback select (concorrência)
        try (PreparedStatement ps = conn.prepareStatement(sel)) {
            ps.setString(1, municipioNome);
            ps.setLong(2, ufId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    municipioCache.put(cacheKey, id);
                    return id;
                }
            }
        }

        return -1;
    }

    private long getOrCreateCategoria(Connection conn, String categoriaNome) throws SQLException {
        if (categoriaNome == null) return -1;
        String key = categoriaNome.trim().toLowerCase();

        if (categoriaCache.containsKey(key)) return categoriaCache.get(key);

        // 1) SELECT
        String sel = "SELECT id FROM categoria_animal WHERE nome = ?";
        try (PreparedStatement ps = conn.prepareStatement(sel)) {
            ps.setString(1, categoriaNome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    categoriaCache.put(key, id);
                    return id;
                }
            }
        }

        // 2) INSERT
        String ins = "INSERT INTO categoria_animal (nome) VALUES (?)";
        try (PreparedStatement psIns = conn.prepareStatement(ins, Statement.RETURN_GENERATED_KEYS)) {
            psIns.setString(1, categoriaNome);
            psIns.executeUpdate();
            try (ResultSet gk = psIns.getGeneratedKeys()) {
                if (gk.next()) {
                    long id = gk.getLong(1);
                    categoriaCache.put(key, id);
                    return id;
                }
            }
        }

        // fallback select
        try (PreparedStatement ps = conn.prepareStatement(sel)) {
            ps.setString(1, categoriaNome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    categoriaCache.put(key, id);
                    return id;
                }
            }
        }

        return -1;
    }
}
