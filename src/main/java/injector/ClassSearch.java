package injector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class search
 *
 * @author Alex Lozovoy
 */

public class ClassSearch {
    public static List<Class<?>> processDirectory(File directory, String pkgname) {

        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

        // Get the list of the files contained in the package
        String[] files = directory.list();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            String className = null;
            if (fileName.endsWith(".java")) {
                className = pkgname + '.' + fileName.substring(0, fileName.length() - 5);
            }
            if (className != null) {
                classes.add(loadClass(className));
            }
            File subdir = new File(directory, fileName);
            if (subdir.isDirectory()) {
                classes.addAll(processDirectory(subdir, pkgname + '.' + fileName));
            }
        }
        return classes;
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
        }
    }
}
