package com.tangcheng.learning.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintRunnable implements Runnable {

    public static final Logger LOGGER = LoggerFactory.getLogger(PrintRunnable.class);

    private Lock lock;
    private Condition condition;
    private String symbol;
    private int flag = 0;
    private static AtomicInteger globalFlag = new AtomicInteger(0);

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
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (globalFlag.get() % 3 != flag) {
                    condition.await();
                }
                LOGGER.info("{}->result:{}", globalFlag.get(), symbol);
                condition.signalAll();
                //计划输出3个周期
                // flag为0的会在6(3+3+flag)时结束，结束前会signalALl,
                // flag为1的会在7(3+3+flag)时结束，结束前会signalALl,
                // flag为2的会在8(3+3+flag)时结束，结束前signalAll(lock相关的对象都操作完了)
                int exitFlag = 3 * 2 + flag;
                int current = globalFlag.get();//这一步很关键，如果不保留当前值，执行getAndIncrement后,就不能按预期break
                globalFlag.getAndIncrement();
                if (current == exitFlag) break;
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        }
        LOGGER.info("end");
    }


}  