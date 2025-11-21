package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class JavaliNormalization {
    private JavaliNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("javali","Javali");
        m.put("javali femea","Javali");
        m.put("javali fêmea","Javali");
        m.put("javali macho","Javali");
        return Collections.unmodifiableMap(m);
    }
}