package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class BovinoNormalization {
    private BovinoNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("bovino", "Bovino");
        m.put("bovino femea", "Bovino");
        m.put("bovino fêmea", "Bovino");
        m.put("bovino macho", "Bovino");
        m.put("novilha intermediaria", "Bovino");
        m.put("novilha intermediária", "Bovino");
        m.put("novilha precoce", "Bovino");
        m.put("novilhona", "Bovino");
        m.put("novilho intermediario", "Bovino");
        m.put("novilho intermediário", "Bovino");
        m.put("novilho precoce", "Bovino");
        m.put("novilhao", "Bovino");
        m.put("novilhão", "Bovino");
        m.put("vaca", "Bovino");
        m.put("vitelo", "Bovino");
        m.put("touro", "Bovino");
        m.put("touro/touruno", "Bovino");
        return Collections.unmodifiableMap(m);
    }
}