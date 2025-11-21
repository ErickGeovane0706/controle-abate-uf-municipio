package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class MuarNormalization {
    private MuarNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("muar", "Muar");
        m.put("mula", "Muar");
        m.put("mula femea", "Muar");
        m.put("mula fêmea", "Muar");
        m.put("mula macho", "Muar");
        m.put("burro-mulo", "Muar");
        return Collections.unmodifiableMap(m);
    }
}