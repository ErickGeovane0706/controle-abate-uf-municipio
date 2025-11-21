package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PangaNormalization {
    private PangaNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("panga","Panga");
        m.put("pangasius","Panga");
        return Collections.unmodifiableMap(m);
    }
}