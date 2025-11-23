package ifpb.bancoDeDados.BancodeDados.controller;

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
}
