package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class JacareNormalization {
    private JacareNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("jacare","Jacaré");
        m.put("jacaré do pantanal","Jacaré");
        m.put("jacaré-do-papo-amarelo","Jacaré");
        return Collections.unmodifiableMap(m);
    }
}
