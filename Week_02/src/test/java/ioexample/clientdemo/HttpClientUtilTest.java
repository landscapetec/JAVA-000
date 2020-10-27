package ioexample.clientdemo;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpClientUtilTest {

    @Test
    public void testDoGet() throws IOException {

        long endTime = System.currentTimeMillis()
                + TimeUnit.SECONDS.toMillis(30);

        int requestCount = 0;
        while (System.currentTimeMillis() < endTime) {
            String result = HttpClientUtil.get("http://www.baidu.com");
            requestCount++;
        }

        System.out.println("HttpClient执行次数" + requestCount);
    }
}
