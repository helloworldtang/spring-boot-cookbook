package com.tangcheng.app.dao.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-25  19:06
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedLockRedisTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(DistributedLockRedisTest.class);

    @Test
    public void lock() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Worker(countDownLatch, "123"));
        executorService.execute(new Worker(countDownLatch, "456"));
        executorService.execute(new Worker(countDownLatch, "789"));
        executorService.shutdown();
        countDownLatch.await();

        /**
         * 输出结果：因为输出流是单线程的。会出现输出混乱的情况
         * 1234
         ==end==
         56
         ==end==
         789
         ==end==
         123
         ==end==
         456
         ==end==
         789
         ==end==
         456
         ==end==
         123
         ==end==
         789
         ==end==
         456
         ==end==
         123
         ==end==
         789
         ==end==
         456
         ==end==
         123
         ==end==
         789
         ==end==
         456
         ==end==
         123
         ==end==
         789
         ==end==
         123
         ==end==
         456
         ==end==
         789
         ==end==
         */
    }


}

class Worker implements Runnable {

    private CountDownLatch countDownLatch;
    private String content;

    public Worker(CountDownLatch countDownLatch, String content) {
        this.countDownLatch = countDownLatch;
        this.content = content;
    }

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (char word : content.toCharArray()) {
                System.out.print(word);
            }
            System.out.println("\n==end==");
        }
        countDownLatch.countDown();

    }
}