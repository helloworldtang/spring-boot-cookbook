package com.tangcheng.learning.thread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by MyWorld on 2016/8/11.
 */
@Slf4j
@Service
public class AsyncTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskService.class);

    @Async
    public void executeAsyncTask(Integer value, CountDownLatch countDownLatch) {
        countDownLatch.countDown();
        LOGGER.info("task 1:{}", value);
    }

    @Async
    public void executeAsyncTaskPlus(Integer value, CountDownLatch countDownLatch) {
        countDownLatch.countDown();
        LOGGER.info("task 1++:{}", value);
    }

    @Async
    public Future<String> asyncWithFuture() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        log.info("{}-{}", "@Async", "future");
        return new AsyncResult<>("hello async future");
    }


}
