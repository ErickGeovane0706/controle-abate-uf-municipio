package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CoelhoNormalization {
    private CoelhoNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("coelho", "Coelho");
        m.put("coelha", "Coelho");
        return Collections.unmodifiableMap(m);
    }
}
