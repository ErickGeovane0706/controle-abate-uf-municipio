package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class TrutaNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("truta", "Truta"),
            entry("truta-arco-íris", "Truta"),
            entry("truta arco-iris", "Truta")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
