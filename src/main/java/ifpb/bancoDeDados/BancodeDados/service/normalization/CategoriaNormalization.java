package ifpb.bancoDeDados.BancodeDados.service.normalization;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class CategoriaNormalization {

    private static final Map<String, String> NORMALIZED_MAP;

    static {
        Map<String, String> temp = new HashMap<>();

        // Pacote onde estão TODAS as classes de normalização
        String basePackage = "ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization";

        try {
            // Carrega todas as classes do pacote base
            Set<Class<?>> normalizationClasses = PackageScanner.getClasses(basePackage);

            for (Class<?> clazz : normalizationClasses) {
                try {
                    // Procura método estático "getMap()"
                    Method method = clazz.getMethod("getMap");

                    // Chama método
                    Map<String, String> map = (Map<String, String>) method.invoke(null);

                    if (map != null) {
                        temp.putAll(map);
                    }

                } catch (NoSuchMethodException ignored) {
                    // A classe não possui getMap(), então ignora
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar normalizações automaticamente", e);
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
