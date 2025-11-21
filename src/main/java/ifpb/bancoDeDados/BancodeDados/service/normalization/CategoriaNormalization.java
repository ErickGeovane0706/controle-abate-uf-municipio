package ifpb.bancoDeDados.BancodeDados.service.normalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public final class CategoriaNormalization {

    private static final Map<String, String> NORMALIZED_MAP;

    static {
        Map<String, String> temp = new HashMap<>();

        ServiceLoader<CategoriaNormalizerProvider> loader = ServiceLoader.load(CategoriaNormalizerProvider.class);
        for (CategoriaNormalizerProvider provider : loader) {
            Map<String, String> map = provider.getMap();
            if (map != null) {
                temp.putAll(map);
            }
        }

        NORMALIZED_MAP = Collections.unmodifiableMap(temp);
    }

    private CategoriaNormalization() {}

    public static String normalizar(String raw) {
        if (raw == null) return null;
        String key = raw.trim().toLowerCase();
        return NORMALIZED_MAP.getOrDefault(key, raw.trim());
    }

    public static Map<String, String> getMap() {
        return NORMALIZED_MAP;
    }
}
