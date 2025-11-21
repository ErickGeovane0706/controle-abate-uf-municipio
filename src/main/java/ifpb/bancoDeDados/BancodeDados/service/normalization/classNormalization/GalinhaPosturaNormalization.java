package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class GalinhaPosturaNormalization {
    private GalinhaPosturaNormalization() {}
    public static Map<String,String> getMap(){
        return Collections.unmodifiableMap(new HashMap<>(){{
            put("galinha de postura","Galinha De Postura");
            put("galinha de postura ","Galinha De Postura");
        }});
    }
}