package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

public final class AvestruzNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.of(
            "avestruz", "Avestruz",
            "avestruz femea", "Avestruz",
            "avestruz fêmea", "Avestruz",
            "avestruz macho", "Avestruz"
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
