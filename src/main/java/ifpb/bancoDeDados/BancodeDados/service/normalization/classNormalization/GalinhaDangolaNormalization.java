package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class GalinhaDangolaNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("galinha dangola", "Galinha-d'angola"),
            entry("galinha-d'angola", "Galinha-d'angola"),
            entry("galinha de água", "Galinha-d'angola")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
