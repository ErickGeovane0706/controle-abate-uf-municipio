package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class CoelhoNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("coelho", "Coelho"),
            entry("coelha", "Coelho")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
