package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class BubalinoNormalization {
    private BubalinoNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("bubalino", "Bubalino");
        m.put("bubalino jovem", "Bubalino");
        m.put("búfala", "Bubalino");
        m.put("bufala", "Bubalino");
        m.put("búfalo", "Bubalino");
        m.put("bufalo", "Bubalino");
        return Collections.unmodifiableMap(m);
    }
}
