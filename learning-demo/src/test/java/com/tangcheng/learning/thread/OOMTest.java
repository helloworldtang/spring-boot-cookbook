package com.tangcheng.learning.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 9/21/2018 8:26 PM
 */
@Slf4j
public class OOMTest {

    @Test
    public void given3Thread_whenOneThreadOOM_thenThreadKeepRunningIfNeedMoreJVMHeap() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread thread1 = new Thread(() -> {
            List<byte[]> list = new ArrayList<>();
            while (true) {
                log.info("11111111");
                byte[] b = new byte[1024 * 1024];
                list.add(b);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (list.size() >= Integer.MAX_VALUE) {
                    break;
                }
            }
            countDownLatch.countDown();

        });
        thread1.setName("thread_1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            int count = 0;
            while (true) {
                log.info("22222222");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                count++;
                if (count >= Integer.MAX_VALUE) {
                    break;
                }
            }
            countDownLatch.countDown();
        });
        thread2.setName("thread_2");
        thread2.start();

        Thread thread3 = new Thread(() -> {
            StringBuilder builder = new StringBuilder();
            int count = 0;
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    builder.append(count);
                    log.info("333:{}", builder.toString());
                    count++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (builder.length() >= Integer.MAX_VALUE) {
                    break;
                }
            }
            countDownLatch.countDown();
        });
        thread3.setName("thread_3");
        thread3.start();

        countDownLatch.await();
    }


}
