package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AsininoNormalization implements CategoriaNormalizerProvider {
    private AsininoNormalization() {}
    private static final Map<String,String> MAP = Map.of(
        "asinino", "Asinino",
        "asinino femea", "Asinino",
        "asinino fêmea", "Asinino",
        "asinino macho", "Asinino",
        "asno", "Asinino",
        "jumento", "Asinino",
        "jegue", "Asinino"
        );

    @Override
    public Map<String, String> getMap() {
        return Map.of();
    }
}