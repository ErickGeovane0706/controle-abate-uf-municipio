package ifpb.bancoDeDados.BancodeDados.repository;

import ifpb.bancoDeDados.BancodeDados.entity.Abate;
import ifpb.bancoDeDados.BancodeDados.entity.CategoriaAnimal;
import ifpb.bancoDeDados.BancodeDados.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbateRepository extends JpaRepository<Abate, Long> {
    Optional<Abate> findByAnoAndMesAndMunicipioAndCategoriaAnimal(
            int ano, int mes,
            Municipio municipio,
            CategoriaAnimal categoriaAnimal
    );
}
