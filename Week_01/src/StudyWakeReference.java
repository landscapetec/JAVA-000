import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class StudyWakeReference {
    public static void main(String[] args) throws InterruptedException {
        byte[] byteData = new byte[1024 * 1024 * 100];
        WeakReference<byte[]> softReference = new WeakReference<>(byteData);

        byteData = null;
        System.out.println("第一次GC前：" + byteData);
        System.out.println("第一次GC前：" + softReference.get());

        System.gc();
        Thread.sleep(500);
        System.out.println("第一次GC后：" + byteData);
        System.out.println("第一次GC后：" + softReference.get());


        byte[] newByteData = new byte[1024 * 1024 * 100];
        System.gc();
        Thread.sleep(500);
        System.out.println("第一次GC后：" + byteData);
        System.out.println("第一次GC后：" + softReference.get());
    }
}
