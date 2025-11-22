package ifpb.bancoDeDados.BancodeDados.repository;

import ifpb.bancoDeDados.BancodeDados.entity.Abate;
import ifpb.bancoDeDados.BancodeDados.entity.CategoriaAnimal;
import ifpb.bancoDeDados.BancodeDados.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbateRepository extends JpaRepository<Abate, Long> {

    List<Abate> findByAnoAndMesAndMunicipioAndCategoriaAnimal(
            int ano,
            int mes,
            Municipio municipio,
            CategoriaAnimal categoriaAnimal
    );

    List<Abate> findByAno(int ano);

    List<Abate> findByAnoAndMes(int ano, int mes);

    List<Abate> findByMunicipio_Nome(String nome);

    List<Abate> findByMunicipio_Uf_Sigla(String siglaUF);

    List<Abate> findByCategoriaAnimal_Nome(String categoria);
}
