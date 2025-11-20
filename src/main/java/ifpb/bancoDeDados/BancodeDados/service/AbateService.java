package ifpb.bancoDeDados.BancodeDados.service;


import ifpb.bancoDeDados.BancodeDados.entity.Abate;
import ifpb.bancoDeDados.BancodeDados.entity.CategoriaAnimal;
import ifpb.bancoDeDados.BancodeDados.entity.Municipio;
import ifpb.bancoDeDados.BancodeDados.entity.UF;
import ifpb.bancoDeDados.BancodeDados.repository.AbateRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AbateService {

    private final UFService ufService;
    private final MunicipioService municipioService;
    private final CategoriaAnimalService categoriaService;
    private final AbateRepository abateRepo;

    public AbateService(UFService ufService,
                        MunicipioService municipioService,
                        CategoriaAnimalService categoriaService,
                        AbateRepository abateRepo) {
        this.ufService = ufService;
        this.municipioService = municipioService;
        this.categoriaService = categoriaService;
        this.abateRepo = abateRepo;
    }

    @Transactional
        public void salvarAbate(int ano, int mes, String siglaUF, String nomeMunicipio, String categoriaNome, long quantidade) {
            UF uf = ufService.findOrCreate(siglaUF);
            Municipio municipio = municipioService.findOrCreate(nomeMunicipio, uf);
            CategoriaAnimal categoria = categoriaService.findOrCreate(categoriaNome);

            if(abateRepo.findByAnoAndMesAndMunicipioAndCategoriaAnimal(ano, mes, municipio, categoria).isEmpty()) {
                Abate abate = Abate.builder()
                        .ano(ano)
                        .mes(mes)
                        .quantidade(quantidade)
                        .municipio(municipio)
                        .categoriaAnimal(categoria)
                        .build();
                abateRepo.save(abate);
            }
            else{
                System.out.println("Abate Ja Cadastrado");
            }

    }
}

