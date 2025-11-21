package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

public final class AvesNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.of(
            "aves", "Aves",
            "ave", "Aves"
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
