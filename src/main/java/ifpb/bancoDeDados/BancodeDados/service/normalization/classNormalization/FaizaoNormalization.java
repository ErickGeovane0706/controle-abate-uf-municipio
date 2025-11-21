package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class FaizaoNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("faizao", "Faizão"),
            entry("faisao", "Faizão")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
