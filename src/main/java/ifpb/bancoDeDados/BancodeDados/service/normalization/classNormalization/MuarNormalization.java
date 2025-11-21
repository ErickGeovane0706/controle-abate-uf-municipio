package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class MuarNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("muar", "Muar"),
            entry("mula", "Muar"),
            entry("mula femea", "Muar"),
            entry("mula fêmea", "Muar"),
            entry("mula macho", "Muar"),
            entry("burro-mulo", "Muar")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
