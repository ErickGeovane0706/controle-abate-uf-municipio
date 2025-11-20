package ifpb.bancoDeDados.BancodeDados.service;

import ifpb.bancoDeDados.BancodeDados.entity.CategoriaAnimal;
import ifpb.bancoDeDados.BancodeDados.repository.CategoriaAnimalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CategoriaAnimalService {
    private final CategoriaAnimalRepository categoriaRepo;

    public CategoriaAnimalService(CategoriaAnimalRepository categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Transactional
    public CategoriaAnimal findOrCreate(String nome) {
        return categoriaRepo.findByNomeIgnoreCase(nome)
                .orElseGet(() -> categoriaRepo.save(new CategoriaAnimal(null, nome)));
    }
}

