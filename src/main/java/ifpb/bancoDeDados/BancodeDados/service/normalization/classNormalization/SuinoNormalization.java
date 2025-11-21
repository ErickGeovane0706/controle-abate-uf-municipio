package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class SuinoNormalization {
    private SuinoNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("porco matrizeiro","Suíno");
        m.put("porca matrizeira","Suíno");
        m.put("porco nao classificado","Suíno");
        m.put("porco não classificado","Suíno");
        m.put("suino femea","Suíno");
        m.put("suíno femea","Suíno");
        m.put("suino fêmea","Suíno");
        m.put("suino macho","Suíno");
        m.put("suíno macho","Suíno");
        m.put("suino tipo banha, femea","Suíno");
        m.put("suino tipo banha, macho","Suíno");
        m.put("suino tipo carne, femea","Suíno");
        m.put("suino tipo carne, macho","Suíno");
        m.put("suíno","Suíno");
        m.put("leitao","Suíno");
        m.put("leitoa","Suíno");
        return Collections.unmodifiableMap(m);
    }
}