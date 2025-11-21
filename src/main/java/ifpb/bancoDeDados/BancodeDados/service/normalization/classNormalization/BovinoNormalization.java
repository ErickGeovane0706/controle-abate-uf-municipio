package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class BovinoNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("bovino", "Bovino"),
            entry("bovino femea", "Bovino"),
            entry("bovino fêmea", "Bovino"),
            entry("bovino macho", "Bovino"),
            entry("novilha intermediaria", "Bovino"),
            entry("novilha intermediária", "Bovino"),
            entry("novilha precoce", "Bovino"),
            entry("novilhona", "Bovino"),
            entry("novilho intermediario", "Bovino"),
            entry("novilho intermediário", "Bovino"),
            entry("novilho precoce", "Bovino"),
            entry("novilhao", "Bovino"),
            entry("novilhão", "Bovino"),
            entry("vaca", "Bovino"),
            entry("vitelo", "Bovino"),
            entry("touro", "Bovino"),
            entry("touro/touruno", "Bovino")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
