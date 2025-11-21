package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class EquinoNormalization {
    private EquinoNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("equino", "Equino");
        m.put("equino femea", "Equino");
        m.put("equino fêmea", "Equino");
        m.put("equino macho", "Equino");
        m.put("cavalo", "Equino");
        m.put("égua", "Equino");
        m.put("egua", "Equino");
        m.put("potro", "Equino");
        m.put("pônei", "Equino");
        m.put("ponei", "Equino");
        m.put("horse", "Equino");
        return Collections.unmodifiableMap(m);
    }
}
