package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CordeiroNormalization {
    private CordeiroNormalization() {}
    public static Map<String,String> getMap(){
        return Collections.unmodifiableMap(new HashMap<>(){{
            put("cordeiro","Cordeiro");
            put("cordeira","Cordeiro");
        }});
    }
}
