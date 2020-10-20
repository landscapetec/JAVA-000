import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    private String path, classLoaderName;

    public MyClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = new byte[0];
        try {
            bytes = getClassByte(name);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getClassByte(String name) throws IOException {
        String classPath = path + name + ".xlass";

        ByteArrayOutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            inputStream = new FileInputStream(classPath);
            int i = 0;
            while ((i = inputStream.read()) != -1) {
                outputStream.write(255 - i);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return outputStream.toByteArray();

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String helloXClassPath = "/Users/yongleitong/Documents/Projects/JAVA-000/Week_01/doc/assert/Hello/";
        MyClassLoader myClassLoader1 = new MyClassLoader(helloXClassPath, "MyClassLoader");
        Class aClass1 = myClassLoader1.loadClass("Hello");

        ClassLoader classLoader = myClassLoader1;
        do {
            System.out.println(classLoader);
        } while ((classLoader = classLoader.getParent()) != null);

        Class aClass2 = myClassLoader1.loadClass("Hello");
        if (aClass1.equals(aClass2)) {
            System.out.println("相同类加载器类结果相同");
        }

        MyClassLoader myClassLoader2 = new MyClassLoader(helloXClassPath, "MyClassLoader");
        Class bClass = myClassLoader2.loadClass("Hello");
        if (aClass1.equals(bClass)) {
            System.out.println("不同类加载器类结果相等");
        }

        Object object = aClass1.newInstance();
        System.out.println(object.getClass().getName());
        Method method = object.getClass().getDeclaredMethod("hello");
        method.invoke(object, null);
    }
}
