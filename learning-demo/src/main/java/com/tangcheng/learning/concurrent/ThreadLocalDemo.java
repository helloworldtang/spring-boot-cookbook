package com.tangcheng.learning.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {

    static ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorService.execute(new Task(i));
        }
        executorService.shutdown();
    }


    /**
     * 各个线程的输出结果之间是隔离的
     * 输出结果：
     * Thread[pool-1-thread-1,5,main]:0== >0
     * Thread[pool-1-thread-1,5,main]:0== >1
     * Thread[pool-1-thread-1,5,main]:0== >3
     * Thread[pool-1-thread-2,5,main]:1== >0
     * Thread[pool-1-thread-2,5,main]:1== >1
     * Thread[pool-1-thread-2,5,main]:1== >3
     * Thread[pool-1-thread-3,5,main]:2== >0
     * Thread[pool-1-thread-3,5,main]:2== >1
     * Thread[pool-1-thread-3,5,main]:2== >3
     */
    private static class Task implements Runnable {
        private int flag;

        Task(int flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                seqNum.set(seqNum.get() + i);
                System.out.println(Thread.currentThread() + ":" + flag + "== >" + seqNum.get());
            }
        }
    }


} 