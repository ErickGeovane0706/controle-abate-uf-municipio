package ifpb.bancoDeDados.BancodeDados;

import ifpb.bancoDeDados.BancodeDados.service.CsvImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancodeDadosApplication implements CommandLineRunner {

	private final CsvImportService csvImportService;

	public BancodeDadosApplication(CsvImportService csvImportService) {
		this.csvImportService = csvImportService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BancodeDadosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//csvImportService.importarCsv("C:/Users/08001/Projetos/BancodeDados/src/main/resources/sigsifquantitativoanimaisabatidoscategoriauf.csv");
	}
}
