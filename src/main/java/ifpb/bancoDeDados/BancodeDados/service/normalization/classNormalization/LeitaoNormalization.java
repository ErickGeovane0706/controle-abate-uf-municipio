package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class LeitaoNormalization {
    private LeitaoNormalization() {}
    public static Map<String,String> getMap(){
        return Collections.unmodifiableMap(new HashMap<>(){{
            put("leitao","Leitão");
            put("leitoa","Leitão");
        }});
    }
}
