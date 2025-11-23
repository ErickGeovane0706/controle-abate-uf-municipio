package ifpb.bancoDeDados.BancodeDados.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalization;
import ifpb.bancoDeDados.BancodeDados.service.validation.*;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CsvImportService {

    private final AbateService abateService;
    ValidatorEngine engine = new ValidatorEngine(
            List.of(
                    new AnoValidoRule(),
                    new MesValidoRule(),
                    new UfValidaRule(),
                    new QuantidadeValidaRule()
            )
    );


    public CsvImportService(AbateService abateService) {
        this.abateService = abateService;
    }

    /**
     * Lê o CSV e salva os registros no banco de dados usando os services.
     *
     * @param caminhoCsv Caminho do arquivo CSV
     */
    public void importarCsv(String caminhoCsv) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(caminhoCsv))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            reader.readNext(); // Pula header
            String[] linha;

            while ((linha = reader.readNext()) != null) {
                try {
                    // 🔥 1. Valida os dados da linha
                    engine.validate(linha);

                    // 🔥 2. Parse seguro dos campos
                    int ano = Integer.parseInt(linha[0]);
                    int mes = Integer.parseInt(linha[1]);
                    String siglaUF = linha[2];
                    String nomeMunicipio = linha[3];

                    // 🔥 3. Normaliza categoria
                    String categoriaRaw = linha[4];
                    String categoriaNormalizada = CategoriaNormalization.normalizar(categoriaRaw);

                    long quantidade = Long.parseLong(linha[5]);

                    // 🔥 4. Salva no banco usando o serviço
                    abateService.salvarAbate(ano, mes, siglaUF, nomeMunicipio, categoriaNormalizada, quantidade);

                } catch (Exception e) {
                    // 🔥 5. Log de erros sem interromper o processamento
                    System.out.println("⚠ Erro na linha: " + String.join(" | ", linha));
                    System.out.println("Motivo: " + e.getMessage());
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }


}
