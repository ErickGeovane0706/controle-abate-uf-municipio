package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class BubalinoNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("bubalino", "Bubalino"),
            entry("bubalino jovem", "Bubalino"),
            entry("búfala", "Bubalino"),
            entry("bufala", "Bubalino"),
            entry("búfalo", "Bubalino"),
            entry("bufalo", "Bubalino")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
