package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class OvinoNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("ovino", "Ovino"),
            entry("ovino femea", "Ovino"),
            entry("ovino fêmea", "Ovino"),
            entry("ovino macho", "Ovino"),
            entry("ovelha", "Ovino"),
            entry("cordeiro", "Ovino"),
            entry("cordeira", "Ovino")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
