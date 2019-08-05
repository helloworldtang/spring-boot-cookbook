package com.tangcheng.learning.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author tangcheng
 * 2017/11/30
 */
@Slf4j
public class TaskTest {

    @Test
    public void submitCallable() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(new Task());
        log.info("after submit");
        log.info("{}", future.get());
        service.shutdown();
    }

    @Test
    public void submitFutureTask() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
        service.submit(futureTask);
        log.info("after submit");
        log.info("{}", futureTask.get());
        service.shutdown();
    }


    @Test
    public void threadFutureTask() throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
        Thread thread = new Thread(futureTask, "Task-Thread");
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        log.info("Thread [{}] is running", Thread.currentThread().getName());
        if (!futureTask.isDone()) {
            log.info("Task is not done");
            TimeUnit.SECONDS.sleep(2);
        }
        log.info("before FutureTask get ");
        int result = futureTask.get();
        log.info("result is {}", result);
    }


}