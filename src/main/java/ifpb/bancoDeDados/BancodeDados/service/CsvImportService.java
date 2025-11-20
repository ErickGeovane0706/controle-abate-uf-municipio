package ifpb.bancoDeDados.BancodeDados.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvImportService {

    private final AbateService abateService;

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
                int ano = Integer.parseInt(linha[0]);
                int mes = Integer.parseInt(linha[1]);
                String siglaUF = linha[2];
                String nomeMunicipio = linha[3];
                String categoria = linha[4];
                long quantidade = Long.parseLong(linha[5]);

                // Chama o service que cuida de persistir no banco
                abateService.salvarAbate(ano, mes, siglaUF, nomeMunicipio, categoria, quantidade);
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
