package com.tangcheng.learning.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintRunnable implements Runnable {
    private Lock lock;
    private Condition condition;
    private String symbol;
    private int flag = 0;
    private static AtomicInteger i = new AtomicInteger(0);

    public PrintRunnable(Lock lock, Condition condition, String symbol, int flag) {
        this.lock = lock;
        this.condition = condition;
        this.symbol = symbol;
        this.flag = flag;
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        String[] signal = {"A", "B", "C"};
        ExecutorService service = Executors.newFixedThreadPool(signal.length);
        for (int i = 0; i < signal.length; i++) {
            service.execute(new PrintRunnable(lock, condition, signal[i], i));
        }
        service.shutdown();
        //todo 目前这种写法有两个线程没有结束，导致无法shutdown
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (i.get() % 3 != flag) {
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //防止过多输出  
            if (i.get() > 8) break;
            System.out.println(Thread.currentThread() + "->" + i.get() + "result " + symbol);
            i.getAndIncrement();
            condition.signalAll();
            lock.unlock();
        }
        System.out.println(Thread.currentThread() + "->end");

    }
}  