package com.tangcheng.learning.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by tangcheng on 7/23/2017.
 */
public class PrintInOrder implements Runnable {

    public static final Logger LOGGER = LoggerFactory.getLogger(PrintInOrder.class);

    public static final int size = 5;

    private final Lock lock;
    private final Condition conditionAwait;
    private final Condition conditionSignal;

    public PrintInOrder(Lock lock, Condition conditionAwait, Condition conditionSignal) {
        this.lock = lock;
        this.conditionAwait = conditionAwait;
        this.conditionSignal = conditionSignal;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            for (int i = 0; i < size; i++) {
                try {
                    conditionAwait.await();
                    LOGGER.info("seq:{}", i);
                    conditionSignal.signalAll();
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        } finally {
            lock.unlock();
        }

    }
}
