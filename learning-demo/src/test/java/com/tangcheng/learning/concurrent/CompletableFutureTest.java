/**
 * @Auther: cheng.tang
 * @Date: 2019/3/2
 * @Description:
 */
package com.tangcheng.learning.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * @Auther: cheng.tang
 * @Date: 2019/3/2
 * @Description:
 */
@Slf4j
public class CompletableFutureTest {


    /**
     * 16:12:01.109 [main] INFO com.tangcheng.learning.concurrent.CompletableFutureTest - start
     * 16:12:01.168 [main] INFO com.tangcheng.learning.concurrent.CompletableFutureTest - 小于coreSize时，每次新建线程，都会打印这个日志。Runtime.getRuntime().availableProcessors():8
     * 16:12:01.171 [main] INFO com.tangcheng.learning.concurrent.CompletableFutureTest - 小于coreSize时，每次新建线程，都会打印这个日志。Runtime.getRuntime().availableProcessors():8
     * 16:12:01.172 [main] INFO com.tangcheng.learning.concurrent.CompletableFutureTest - 小于coreSize时，每次新建线程，都会打印这个日志。Runtime.getRuntime().availableProcessors():8
     * 16:12:03.172 [Thread-2] INFO com.tangcheng.learning.concurrent.CompletableFutureTest - finish :3
     * 16:12:04.172 [Thread-0] INFO com.tangcheng.learning.concurrent.CompletableFutureTest - finish :1
     * 16:12:05.172 [Thread-1] INFO com.tangcheng.learning.concurrent.CompletableFutureTest - finish :2
     * 16:12:05.172 [main] INFO com.tangcheng.learning.concurrent.CompletableFutureTest - last log
     */
    @Test
    public void testCompletableFuture() {

        log.info("start");
        /**
         * runSync不要求有返回值
         */
        CompletableFuture[] completableFutures = Stream.of(1, 2, 3).map(item -> CompletableFuture.supplyAsync(() -> {
            try {
                int timeout = ThreadLocalRandom.current().nextInt(1, 5);
                if (timeout == 1) {
                    throw new IllegalArgumentException("出错了，为什么是1");
                }
                TimeUnit.SECONDS.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("finish :{}", item);
            return "finish" + item;
        }, executor).exceptionally(ex -> {
                    log.error("exceptionally 一个任务失败了 item:{},{}", item, ex.getMessage());
                    return "一个任务失败了" + ex.getMessage();
                }
        )).toArray(CompletableFuture[]::new);
        //allOf():工厂方法接受由CompletableFuture对象构成的数组,数组中所有的CompletableFuture完成后它返回一个CompletableFuture<Void>对象。
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(completableFutures);
        //等待所有的子线程执行完毕
        voidCompletableFuture.join();
        log.info("last log ");
    }

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(Runtime.getRuntime().availableProcessors(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            log.info("小于coreSize时，每次新建线程，都会打印这个日志。Runtime.getRuntime().availableProcessors():{}", Runtime.getRuntime().availableProcessors());
            Thread t = new Thread(r);
            // 使用守护线程---这种方式不会阻止程序关掉
            t.setDaemon(true);
            return t;
        }
    });

}
