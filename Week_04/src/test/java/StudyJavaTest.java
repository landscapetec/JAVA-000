import org.apache.commons.collections.list.SynchronizedList;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class StudyJavaTest {
    @Test
    public void testCurrentLinkedQueue() {
        ConcurrentLinkedDeque<Integer> queue = new ConcurrentLinkedDeque<Integer>();
        // DelayQueue<Integer> queue=new DelayQueue<Integer>();
//        ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(100);
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    @Test
    public void testDelayQueue() {
        BlockingQueue<DelayTask> queue = new DelayQueue<>();

        queue.offer(new DelayTask(3000, "aaaa"));
        queue.offer(new DelayTask(2000, "bbb"));

        while (!queue.isEmpty()) {
            DelayTask delayTask = queue.poll();
            if (delayTask != null)
                System.out.println(delayTask.getData());
        }

    }

    @Test
    public void testProQueue() {
        BlockingQueue<String> queue = new PriorityBlockingQueue<String>(10, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        queue.offer("a");
        queue.offer("bb");
        queue.offer("ccc");
        queue.offer("dd");
        queue.offer("1");

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    @Test
    public void testConcurrentSet() {
        List<String> list = new ArrayList<>();
        List<String> list1 = new Vector<>();
        List<String> list2 = Collections.synchronizedList(list);
    }

    @Test
    public void testLongAdder() {
        LongAdder longAdder = new LongAdder();
        while (true) {
            System.out.println("11");
            longAdder.increment();
            if(longAdder.longValue()==10)
                break;;
        }
        System.out.println(longAdder.longValue());
    }
}
