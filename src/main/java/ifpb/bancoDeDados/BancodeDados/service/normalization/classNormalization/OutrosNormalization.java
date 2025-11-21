package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import ifpb.bancoDeDados.BancodeDados.service.normalization.CategoriaNormalizerProvider;
import java.util.Map;

import static java.util.Map.entry;

public final class OutrosNormalization implements CategoriaNormalizerProvider {

    private static final Map<String,String> MAP = Map.ofEntries(
            entry("nonato", "Nonato"),
            entry("outros anfíbios", "Outros anfíbios"),
            entry("queixada", "Queixada"),
            entry("ratitas", "Ratitas"),
            entry("paca femea", "Paca"),
            entry("paca fêmea", "Paca"),
            entry("paca macho", "Paca")
    );

    @Override
    public Map<String, String> getMap() {
        return MAP;
    }
}
