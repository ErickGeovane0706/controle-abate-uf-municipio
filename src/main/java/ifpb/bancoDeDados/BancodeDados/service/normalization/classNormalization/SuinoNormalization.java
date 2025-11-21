package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class SuinoNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("porco matrizeiro", "Suíno"),
            entry("porca matrizeira", "Suíno"),
            entry("porco nao classificado", "Suíno"),
            entry("porco não classificado", "Suíno"),
            entry("suino femea", "Suíno"),
            entry("suíno femea", "Suíno"),
            entry("suino fêmea", "Suíno"),
            entry("suino macho", "Suíno"),
            entry("suíno macho", "Suíno"),
            entry("suino tipo banha, femea", "Suíno"),
            entry("suino tipo banha, macho", "Suíno"),
            entry("suino tipo carne, femea", "Suíno"),
            entry("suino tipo carne, macho", "Suíno"),
            entry("suíno", "Suíno"),
            entry("leitao", "Suíno"),
            entry("leitoa", "Suíno")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
