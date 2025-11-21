package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class TilapiaNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("tilapia", "Tilápia"),
            entry("tilápia", "Tilápia"),
            entry("tilápia do nilo", "Tilápia"),
            entry("tilápia-do-nilo", "Tilápia"),
            entry("tilápia, tilápia-do-nilo", "Tilápia")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
