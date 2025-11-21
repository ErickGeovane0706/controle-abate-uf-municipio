package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class GalinhaDomesticaNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("frango", "Galinha Doméstica"),
            entry("frango especial", "Galinha Doméstica"),
            entry("galeto", "Galinha Doméstica"),
            entry("galinha", "Galinha Doméstica"),
            entry("galo", "Galinha Doméstica")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
