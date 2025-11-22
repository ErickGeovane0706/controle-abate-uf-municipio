package ifpb.bancoDeDados.BancodeDados.service;


import ifpb.bancoDeDados.BancodeDados.entity.Abate;
import ifpb.bancoDeDados.BancodeDados.entity.CategoriaAnimal;
import ifpb.bancoDeDados.BancodeDados.entity.Municipio;
import ifpb.bancoDeDados.BancodeDados.entity.UF;
import ifpb.bancoDeDados.BancodeDados.repository.AbateRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

        boolean existe = !abateRepo
                .findByAnoAndMesAndMunicipioAndCategoriaAnimal(ano, mes, municipio, categoria)
                .isEmpty();

        if (!existe) {
            Abate abate = Abate.builder()
                    .ano(ano)
                    .mes(mes)
                    .quantidade(quantidade)
                    .municipio(municipio)
                    .categoriaAnimal(categoria)
                    .build();

            abateRepo.save(abate);
        } else {
            System.out.println("⚠ Abate já cadastrado!");
        }
    }

    public List<Abate> listarTodos() {
        return abateRepo.findAll();
    }

    public List<Abate> buscarPorAno(int ano) {
        return abateRepo.findByAno(ano);
    }

    public List<Abate> buscarPorAnoMes(int ano, int mes) {
        return abateRepo.findByAnoAndMes(ano, mes);
    }

    public List<Abate> buscarPorUf(String uf) {
        return abateRepo.findByMunicipio_Uf_Sigla(uf);
    }

    public List<Abate> buscarPorMunicipio(String municipio) {
        return abateRepo.findByMunicipio_Nome(municipio);
    }

    public List<Abate> buscarPorCategoria(String categoria) {
        return abateRepo.findByCategoriaAnimal_Nome(categoria);
    }
}


