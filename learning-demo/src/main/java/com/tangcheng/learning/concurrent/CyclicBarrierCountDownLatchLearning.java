package com.tangcheng.learning.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierCountDownLatchLearning {

    private static final Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierCountDownLatchLearning.class);

    public static void main(String[] args) throws InterruptedException {

        int parties = 4;
        int count = parties + 1;
        CountDownLatch startGate = new CountDownLatch(count);
        CyclicBarrier barrier = new CyclicBarrier(parties);
        for (int i = 0; i < parties; i++) {
            Thread thread = new Thread(new Writer(barrier, startGate), "demo" + i);
            thread.start();
        }

        LOGGER.info(" ");
        int timeout = 6;
        LOGGER.info("主线程 还需要再阻塞{}s",timeout);
        new Thread(() -> {
            try {
                LOGGER.info("现在开始{}s等待。 CountDownLatch才允许主线程结束阻塞状态",timeout);
                TimeUnit.SECONDS.sleep(timeout);
                startGate.countDown();
                LOGGER.info("{}s等待结束.主线程可以往下走了",timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        startGate.await();//当前线程(main线程)被阻塞，直到startGate的countDown方法的次数和设定的值相同，其它的不要求

        LOGGER.info("======不管Writer线程是否完成，只要countDown的调用次数够了，CountDownLatch就会解除主线程的阻塞状态======");
        LOGGER.info(" ");
        TimeUnit.SECONDS.sleep(10);
        LOGGER.info(" ");
        LOGGER.info("======================CyclicBarrier的可重用性开始======================");
        LOGGER.info(" ");
        int lessParties = parties - 1;
        for (int i = 0; i < lessParties; i++) {
            Thread thread = new Thread(new Writer(barrier, startGate), "damo" + (i + parties));
            thread.start();
        }
        startGate.await();//不会阻塞主线程运行，但write线程会执行不完
        LOGGER.info("目前只调用{}次countDown，虽然小于设置的count {},但仍然不会阻塞主线程",lessParties,count);
        LOGGER.info("CyclicBarrier仍然需要{}个线程都至barrier状态，否则相关的Write线程都需要阻塞。目前只有{}个parties。" +
                "\n 因此所有的Writer线程会一起阻塞下去forever until server shutdown",parties,lessParties);
    }

    private static class Writer implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private CountDownLatch startGate;

        Writer(CyclicBarrier cyclicBarrier, CountDownLatch startGate) {
            this.cyclicBarrier = cyclicBarrier;
            this.startGate = startGate;
        }

        @Override
        public void run() {
            try {
                startGate.countDown();
                LOGGER.info("线程" + Thread.currentThread().getName() + "cyclicBarrier.await()前执行，等待其他线程写入完毕");
                cyclicBarrier.await();
                TimeUnit.SECONDS.sleep(10);//sleep一会。这样日志中可以很好的看到cyclicBarrier.await()的可重用性r
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            LOGGER.info(Thread.currentThread().getName() + "cyclicBarrier.await()后执行......");
        }
    }

}