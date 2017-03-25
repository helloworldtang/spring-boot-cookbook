package com.tangcheng.demo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by MyWorld on 2016/8/11.
 */
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


}
