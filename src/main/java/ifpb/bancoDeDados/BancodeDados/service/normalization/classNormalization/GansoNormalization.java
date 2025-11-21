package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class GansoNormalization {
    private GansoNormalization() {}
    public static Map<String,String> getMap(){
        return Collections.unmodifiableMap(new HashMap<>(){{
            put("ganso","Ganso");
        }});
    }
}
