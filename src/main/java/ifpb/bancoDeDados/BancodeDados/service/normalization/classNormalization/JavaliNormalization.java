package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class JavaliNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("javali", "Javali"),
            entry("javali femea", "Javali"),
            entry("javali fêmea", "Javali"),
            entry("javali macho", "Javali")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
