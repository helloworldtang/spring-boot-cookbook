package com.demo.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Created by MyWorld on 2016/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TaskExecutorConfig.class})
public class AsyncTaskServiceTest {
    @Autowired
    AsyncTaskService asyncTaskService;

    @Test
    public void testAsyncValue() throws InterruptedException {
        int count = 20;
        CountDownLatch countDownLatch = new CountDownLatch(2 * count);
        for (int i = 0; i < count; i++) {
            asyncTaskService.executeAsyncTask(i, countDownLatch);
            asyncTaskService.executeAsyncTaskPlus(i, countDownLatch);
        }
        countDownLatch.await();
    }

}