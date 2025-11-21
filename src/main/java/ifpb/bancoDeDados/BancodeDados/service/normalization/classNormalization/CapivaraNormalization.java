package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CapivaraNormalization {
    private CapivaraNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("capivara", "Capivara");
        return Collections.unmodifiableMap(m);
    }
}
