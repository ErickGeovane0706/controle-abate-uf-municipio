package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class EquinoNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("equino", "Equino"),
            entry("equino femea", "Equino"),
            entry("equino fêmea", "Equino"),
            entry("equino macho", "Equino"),
            entry("cavalo", "Equino"),
            entry("égua", "Equino"),
            entry("egua", "Equino"),
            entry("potro", "Equino"),
            entry("pônei", "Equino"),
            entry("ponei", "Equino"),
            entry("horse", "Equino")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
