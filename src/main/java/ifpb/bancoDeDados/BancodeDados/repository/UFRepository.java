package ifpb.bancoDeDados.BancodeDados.repository;

import ifpb.bancoDeDados.BancodeDados.entity.UF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UFRepository extends JpaRepository<UF, Long> {
    Optional<UF> findBySiglaIgnoreCase(String sigla);
}
