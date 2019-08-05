package com.tangcheng.learning.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by tangcheng on 8/6/2017.
 */
public class VisitCounterTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(VisitCounterTest.class);

    @Test
    public void incrementTest() throws Exception {
        String uri = "http://localhost:8080/user/1";
        VisitCounter visitCounter = new VisitCounter();

        int size = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        ExecutorService executorService = new ThreadPoolExecutor(3,
                20,
                5,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(2000),
                new DefaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()//在部分任务都在新线程中进行的，因为maximumPoolSize比较小，队列长度不够深
        );

        for (int i = 0; i < size; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        visitCounter.increment(uri);
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        LOGGER.warn(e.getMessage(), e);
                    }
                }
            });
        }

        countDownLatch.await();

        assertThat((long) size, is(visitCounter.get(uri)));
    }


}