package ifpb.bancoDeDados.BancodeDados.repository.JDBC;

import ifpb.bancoDeDados.BancodeDados.entity.Abate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AbateJdbcRepository {

    private final DataSource dataSource;

    public void salvarEmBatch(List<Abate> lista) throws Exception {

        String sql = """
            INSERT INTO abate (ano, mes, quantidade, municipio_id, categoria_id)
            VALUES (?, ?, ?, 
                (SELECT id FROM municipio m JOIN uf u ON m.uf_id = u.id WHERE m.nome = ? AND u.sigla = ? LIMIT 1),
                (SELECT id FROM categoria_animal WHERE nome = ? LIMIT 1)
            )
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int count = 0;

            for (Abate a : lista) {

                ps.setInt(1, a.getAno());
                ps.setInt(2, a.getMes());
                ps.setLong(3, a.getQuantidade());
                ps.setString(4, a.getMunicipio().getNome());
                ps.setString(5, a.getMunicipio().getUf().getNome());
                ps.setString(6, a.getCategoriaAnimal().getNome());

                ps.addBatch();

                if (++count % 1000 == 0) {
                    ps.executeBatch();
                }
            }

            ps.executeBatch(); // executa o restante

            System.out.println("✔ Inserção em batch concluída!");
        }
    }
}