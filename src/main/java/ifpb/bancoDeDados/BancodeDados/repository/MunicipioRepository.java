package ifpb.bancoDeDados.BancodeDados.repository;

import ifpb.bancoDeDados.BancodeDados.entity.Municipio;
import ifpb.bancoDeDados.BancodeDados.entity.UF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    Optional<Municipio> findByNomeAndUf(String nome, UF uf);
}
