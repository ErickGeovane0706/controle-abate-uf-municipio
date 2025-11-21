package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class OutrosNormalization {
    private OutrosNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("nonato","Nonato");
        m.put("outros anfíbios","Outros anfíbios");
        m.put("queixada","Queixada");
        m.put("ratitas","Ratitas");
        m.put("paca femea","Paca");
        m.put("paca fêmea","Paca");
        m.put("paca macho","Paca");
        return Collections.unmodifiableMap(m);
    }
}