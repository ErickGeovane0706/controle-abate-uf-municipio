package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AvesNormalization implements CategoriaNormalizerProvider {
    private AvesNormalization() {}
    private static final Map<String,String> MAP = Map.of(
        "aves", "Aves",
        "ave", "Aves");

    @Override
    public Map<String, String> getMap() {
        return Map.of();
    }
}
