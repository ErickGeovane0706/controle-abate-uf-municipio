package ifpb.bancoDeDados.BancodeDados.repository;

import ifpb.bancoDeDados.BancodeDados.entity.Abate;
import ifpb.bancoDeDados.BancodeDados.entity.CategoriaAnimal;
import ifpb.bancoDeDados.BancodeDados.entity.Municipio;
import ifpb.bancoDeDados.BancodeDados.record.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    //Consultas Agregadas
        @Query("""
        SELECT new ifpb.bancoDeDados.BancodeDados.record.SomaPorUfRecord(
            m.uf.sigla, SUM(a.quantidade)
        )
        FROM Abate a
        JOIN a.municipio m
        GROUP BY m.uf.sigla
        ORDER BY m.uf.sigla
    """)
        List<SomaPorUfRecord> somarPorUf();
    @Query("""
       SELECT new ifpb.bancoDeDados.BancodeDados.record.SomaPorMunicipioRecord(
           m.nome, SUM(a.quantidade)
       )
       FROM Abate a
       JOIN a.municipio m
       GROUP BY m.nome
       ORDER BY SUM(a.quantidade) DESC
       """)
    List<SomaPorMunicipioRecord> somarPorMunicipio();
    @Query("""
       SELECT new ifpb.bancoDeDados.BancodeDados.record.SomaPorCategoriaRecord(
           c.nome, SUM(a.quantidade)
       )
       FROM Abate a
       JOIN a.categoriaAnimal c
       GROUP BY c.nome
       ORDER BY SUM(a.quantidade) DESC
       """)
    List<SomaPorCategoriaRecord> somarPorCategoria();
    @Query("""
       SELECT new ifpb.bancoDeDados.BancodeDados.record.SomaPorAnoRecord(
           a.ano, SUM(a.quantidade)
       )
       FROM Abate a
       GROUP BY a.ano
       ORDER BY a.ano
       """)
    List<SomaPorAnoRecord> somarPorAno();
    @Query("""
    SELECT new ifpb.bancoDeDados.BancodeDados.record.SomaFiltradaRecord(
        SUM(a.quantidade)
    )
    FROM Abate a
    WHERE (:uf IS NULL OR a.municipio.uf.sigla = :uf)
      AND (:categoria IS NULL OR a.categoriaAnimal.nome = :categoria)
      AND (:ano IS NULL OR a.ano = :ano)
      AND (:mes IS NULL OR a.mes = :mes)
""")
    SomaFiltradaRecord somaFiltrada(
            @Param("uf") String uf,
            @Param("categoria") String categoria,
            @Param("ano") Integer ano,
            @Param("mes") Integer mes
    );





}
