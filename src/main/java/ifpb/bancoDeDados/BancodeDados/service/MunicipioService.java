package ifpb.bancoDeDados.BancodeDados.service;

import ifpb.bancoDeDados.BancodeDados.entity.Municipio;
import ifpb.bancoDeDados.BancodeDados.entity.UF;
import ifpb.bancoDeDados.BancodeDados.repository.MunicipioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MunicipioService {
    private final MunicipioRepository municipioRepo;

    public MunicipioService(MunicipioRepository municipioRepo) {
        this.municipioRepo = municipioRepo;
    }

    @Transactional
    public Municipio findOrCreate(String nome, UF uf) {
        return municipioRepo.findByNomeAndUf(nome, uf)
                .orElseGet(() -> municipioRepo.save(
                        Municipio.builder()
                                .nome(nome)
                                .uf(uf)
                                .build()
                ));
    }

}

