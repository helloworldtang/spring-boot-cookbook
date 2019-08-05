package com.tangcheng.learning.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MyWorld on 2016/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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

    @Test
    public void SHOULD_GET_FUTURE_RESULT_WHEN_RETURN_ASYNCRESULT() throws InterruptedException, TimeoutException, ExecutionException {
        Future<String> future = asyncTaskService.asyncWithFuture();
        String result = future.get(1, TimeUnit.MINUTES);
        assertThat(result).isEqualTo("hello async future");
    }

}