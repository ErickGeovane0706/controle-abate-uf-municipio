package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class BagreAfricanoNormalization {
    private BagreAfricanoNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("bagre africano", "Bagre africano");
        m.put("bagre", "Bagre africano");
        return Collections.unmodifiableMap(m);
    }
}
