package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class TrutaNormalization {
    private TrutaNormalization() {}
    public static Map<String,String> getMap(){
        return Collections.unmodifiableMap(new HashMap<>(){{
            put("truta","Truta");
            put("truta-arco-íris","Truta");
            put("truta arco-iris","Truta");
        }});
    }
}
