package ifpb.bancoDeDados.BancodeDados.service.normalization;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class PackageScanner {

    public static Set<Class<?>> getClasses(String packageName) throws Exception {
        Set<Class<?>> classes = new HashSet<>();
        String path = packageName.replace('.', '/');
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);

        if (url == null) return classes;

        File directory = new File(url.getFile());
        if (!directory.exists()) return classes;

        for (String file : directory.list()) {
            if (file.endsWith(".class")) {
                String className = packageName + '.' + file.substring(0, file.length() - 6);
                classes.add(Class.forName(className));
            }
        }

        return classes;
    }
}
