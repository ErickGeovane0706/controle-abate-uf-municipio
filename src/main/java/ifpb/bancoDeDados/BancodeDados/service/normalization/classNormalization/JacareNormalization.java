package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class JacareNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("jacare", "Jacaré"),
            entry("jacaré do pantanal", "Jacaré"),
            entry("jacaré-do-papo-amarelo", "Jacaré")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
