package ifpb.bancoDeDados.BancodeDados.service;

import ifpb.bancoDeDados.BancodeDados.entity.UF;
import ifpb.bancoDeDados.BancodeDados.repository.UFRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UFService {
    private final UFRepository ufRepo;

    // Mapa fixo de sigla -> nome da UF
    private static final Map<String, String> UF_MAP = new HashMap<>();
    static {
        UF_MAP.put("AC","Acre"); UF_MAP.put("AL","Alagoas"); UF_MAP.put("AP","Amapá");
        UF_MAP.put("AM","Amazonas"); UF_MAP.put("BA","Bahia"); UF_MAP.put("CE","Ceará");
        UF_MAP.put("DF","Distrito Federal"); UF_MAP.put("ES","Espírito Santo"); UF_MAP.put("GO","Goiás");
        UF_MAP.put("MA","Maranhão"); UF_MAP.put("MT","Mato Grosso"); UF_MAP.put("MS","Mato Grosso do Sul");
        UF_MAP.put("MG","Minas Gerais"); UF_MAP.put("PA","Pará"); UF_MAP.put("PB","Paraíba");
        UF_MAP.put("PR","Paraná"); UF_MAP.put("PE","Pernambuco"); UF_MAP.put("PI","Piauí");
        UF_MAP.put("RJ","Rio de Janeiro"); UF_MAP.put("RN","Rio Grande do Norte"); UF_MAP.put("RS","Rio Grande do Sul");
        UF_MAP.put("RO","Rondônia"); UF_MAP.put("RR","Roraima"); UF_MAP.put("SC","Santa Catarina");
        UF_MAP.put("SP","São Paulo"); UF_MAP.put("SE","Sergipe"); UF_MAP.put("TO","Tocantins");
    }

    public UFService(UFRepository ufRepo) {
        this.ufRepo = ufRepo;
    }

    @Transactional
    public UF findOrCreate(String sigla) {
        return ufRepo.findBySiglaIgnoreCase(sigla)
                .orElseGet(() -> ufRepo.save(new UF(null, sigla, UF_MAP.get(sigla))));
    }
}
