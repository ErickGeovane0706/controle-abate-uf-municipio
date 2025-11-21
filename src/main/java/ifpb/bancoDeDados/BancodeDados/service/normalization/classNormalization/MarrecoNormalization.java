package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class MarrecoNormalization {
    private MarrecoNormalization() {}
    public static Map<String,String> getMap(){
        return Collections.unmodifiableMap(new HashMap<>(){{
            put("marreco","Marreco");
        }});
    }
}