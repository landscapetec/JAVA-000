import sun.tools.jconsole.inspector.XObject;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL url : urls) {
            System.out.println("===>" + url.toExternalForm());
        }

        //扩展类加载器
        PrintClassLoader("扩展类加载器",JvmClassLoaderPrintPath.class.getClassLoader().getParent());

        //应用类加载器
        PrintClassLoader("应用类加载器",JvmClassLoaderPrintPath.class.getClassLoader());
    }

    public static void PrintClassLoader(String name, ClassLoader cl) {
        if (cl != null) {
            System.out.println(name + " ClassLoader" + cl.toString());
            printURLClassLoader(cl);
        } else {
            System.out.println("ClassLoader is null");
        }
    }

    private static void printURLClassLoader(ClassLoader cl) {
        Object ucp = insightField(cl, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object p : ps) {
            System.out.println("===>" + p.toString());
        }
    }

    private static Object insightField(Object obj, String fName) {
        try {
            Field f = null;
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
