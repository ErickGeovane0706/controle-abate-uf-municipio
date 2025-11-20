package ifpb.bancoDeDados.BancodeDados.repository;

import ifpb.bancoDeDados.BancodeDados.entity.Abate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbateRepository extends JpaRepository<Abate, Long> {
}
