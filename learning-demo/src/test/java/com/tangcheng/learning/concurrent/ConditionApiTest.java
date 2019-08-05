package com.tangcheng.learning.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tangcheng on 7/23/2017.
 */
public class ConditionApiTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(ConditionApiTest.class);

    @Test
    public void printInOrderTest() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        PrintInOrder printInOrderA = new PrintInOrder(lock, conditionA, conditionB);
        PrintInOrder printInOrderB = new PrintInOrder(lock, conditionB, conditionC);
        PrintInOrder printInOrderC = new PrintInOrder(lock, conditionC, conditionA);
        Thread[] threads = new Thread[]{
                new Thread(printInOrderA, "threadA"),
                new Thread(printInOrderB, "threadB"),
                new Thread(printInOrderC, "threadC")};
        LOGGER.info("begin");

        for (Thread thread : threads) {
            thread.start();
        }
        conditionA.signalAll();
        for (Thread thread : threads) {
            thread.join();
        }
        LOGGER.info("end");
    }


}
