package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CamaraoNormalization {
    private CamaraoNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("camarao", "Camarão");
        m.put("camarão", "Camarão");
        return Collections.unmodifiableMap(m);
    }
}
