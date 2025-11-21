package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class CaprinoNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("caprino femea", "Caprino"),
            entry("caprino fêmea", "Caprino"),
            entry("caprino macho", "Caprino"),
            entry("cabrita", "Caprino"),
            entry("cabrito", "Caprino")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
