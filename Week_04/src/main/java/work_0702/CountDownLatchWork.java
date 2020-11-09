package work_0702;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchWork {

    private static int result;


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new CountDownLatchWork.SumRunable(countDownLatch)).start();
        countDownLatch.await();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        //result = sum(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
    }

    static class SumRunable implements Runnable{
        private final CountDownLatch countDownLatch;

        SumRunable(CountDownLatch countDownLatch){
            this.countDownLatch=countDownLatch;
        }

        public void run() {
            result=sum();
            countDownLatch.countDown();
        }
    }
    private static int sum() {
        result=fibo(36);
        return result;
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
