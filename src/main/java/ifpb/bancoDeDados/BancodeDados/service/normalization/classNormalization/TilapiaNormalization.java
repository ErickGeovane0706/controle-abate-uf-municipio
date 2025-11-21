package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class TilapiaNormalization {
    private TilapiaNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("tilapia","Tilápia");
        m.put("tilápia","Tilápia");
        m.put("tilápia do nilo","Tilápia");
        m.put("tilápia-do-nilo","Tilápia");
        m.put("tilápia, tilápia-do-nilo","Tilápia");
        return Collections.unmodifiableMap(m);
    }
}