package ifpb.bancoDeDados.BancodeDados.repository;

import ifpb.bancoDeDados.BancodeDados.entity.CategoriaAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaAnimalRepository extends JpaRepository<CategoriaAnimal, Long> {
    Optional<CategoriaAnimal> findByNomeIgnoreCase(String nome);
}
