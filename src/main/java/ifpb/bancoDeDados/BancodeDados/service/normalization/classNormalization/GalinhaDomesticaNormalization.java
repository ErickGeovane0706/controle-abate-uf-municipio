package ifpb.bancoDeDados.BancodeDados.service.normalization.classNormalization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class GalinhaDomesticaNormalization {
    private GalinhaDomesticaNormalization() {}
    public static Map<String,String> getMap(){
        Map<String,String> m = new HashMap<>();
        m.put("frango","Galinha Doméstica");
        m.put("frango especial","Galinha Doméstica");
        m.put("galeto","Galinha Doméstica");
        m.put("galinha","Galinha Doméstica");
        m.put("galo","Galinha Doméstica");
        return Collections.unmodifiableMap(m);
    }
}
