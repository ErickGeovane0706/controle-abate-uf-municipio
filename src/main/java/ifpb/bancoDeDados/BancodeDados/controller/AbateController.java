package ifpb.bancoDeDados.BancodeDados.controller;

import ifpb.bancoDeDados.BancodeDados.record.*;
import ifpb.bancoDeDados.BancodeDados.service.AbateService;
import ifpb.bancoDeDados.BancodeDados.entity.Abate;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/abates")
public class AbateController {
    private final AbateService abateService;

    public AbateController(AbateService abateService) {
        this.abateService = abateService;
    }
    // ---------------------------------------------------------
    // 🔹 CONSULTAS BÁSICAS
    // ---------------------------------------------------------

    @GetMapping
    public List<Abate> listarTodos() {
        return abateService.listarTodos();
    }

    @GetMapping("/ano/{ano}")
    public List<Abate> porAno(@PathVariable int ano) {//Buscar por Ano
        return abateService.buscarPorAno(ano);
    }

    @GetMapping("/ano/{ano}/mes/{mes}")
    public List<Abate> porAnoMes(@PathVariable int ano, @PathVariable int mes) {//Buscar ano e mes
        return abateService.buscarPorAnoMes(ano, mes);
    }

    @GetMapping("/uf/{uf}")
    public List<Abate> porUf(@PathVariable String uf) {//Buscar po UF
        return abateService.buscarPorUf(uf);
    }

    @GetMapping("/municipio/{municipio}")
    public List<Abate> porMunicipio(@PathVariable String municipio) {//Buscar por Municipior
        return abateService.buscarPorMunicipio(municipio);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Abate> porCategoria(@PathVariable String categoria) {//Buscar por categoria
        return abateService.buscarPorCategoria(categoria);
    }
    @GetMapping("/soma-por-uf")
    public List<SomaPorUfRecord> somaPorUf() {
        return abateService.somarPorUf();
    }
    @GetMapping("/soma/municipio")
    public List<SomaPorMunicipioRecord> somarPorMunicipio() {
        return abateService.somarPorMunicipio();
    }

    @GetMapping("/soma/categoria")
    public List<SomaPorCategoriaRecord> somarPorCategoria() {
        return abateService.somarPorCategoria();
    }
    @GetMapping("/soma/ano")
    public List<SomaPorAnoRecord> somarPorAno() {
        return abateService.somarPorAno();
    }
    @GetMapping("/soma-filtrada")
    public SomaFiltradaRecord somaFiltrada(
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) Integer mes
    ) {
        return abateService.somaFiltrada(uf, categoria, ano, mes);
    }



}
