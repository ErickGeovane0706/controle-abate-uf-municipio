package ifpb.bancoDeDados.BancodeDados.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
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
                    // 🔥 1. Valida antes de converter
                    engine.validate(linha);

                    // 🔥 2. Agora sim faz parse com segurança
                    int ano = Integer.parseInt(linha[0]);
                    int mes = Integer.parseInt(linha[1]);
                    String siglaUF = linha[2];
                    String nomeMunicipio = linha[3];
                    String categoria = linha[4];
                    long quantidade = Long.parseLong(linha[5]);

                    // 🔥 3. Usa serviço para salvar
                    abateService.salvarAbate(ano, mes, siglaUF, nomeMunicipio, categoria, quantidade);

                } catch (Exception e) {
                    // 🔥 4. Não interrompe o processamento — apenas loga
                    System.out.println("⚠ Erro na linha: " + String.join(" | ", linha));
                    System.out.println("Motivo: " + e.getMessage());
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

}
