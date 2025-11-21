package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class OvinoNormalization {
    private OvinoNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("ovino","Ovino");
        m.put("ovino femea","Ovino");
        m.put("ovino fêmea","Ovino");
        m.put("ovino macho","Ovino");
        m.put("ovelha","Ovino");
        m.put("cordeiro","Ovino");
        m.put("cordeira","Ovino");
        return Collections.unmodifiableMap(m);
    }
}