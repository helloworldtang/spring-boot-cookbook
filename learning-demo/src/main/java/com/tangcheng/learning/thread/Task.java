package com.tangcheng.learning.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author tangcheng
 * 2017/11/30
 */
@Slf4j
public class Task implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        log.info("Thread [{}] is running", Thread.currentThread().getName());
        int result = 0;
        for (int i = 0; i < 100; i++) {
            result += i;
        }
        TimeUnit.SECONDS.sleep(3);
        return result;
    }


}
