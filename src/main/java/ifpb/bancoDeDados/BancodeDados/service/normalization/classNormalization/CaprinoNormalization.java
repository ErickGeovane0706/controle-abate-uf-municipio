package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CaprinoNormalization {
    private CaprinoNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("caprino femea", "Caprino");
        m.put("caprino fêmea", "Caprino");
        m.put("caprino macho", "Caprino");
        m.put("cabrita", "Caprino");
        m.put("cabrito", "Caprino");
        return Collections.unmodifiableMap(m);
    }
}