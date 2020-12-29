package com.tangcheng.learning.concurrent.conidtion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintWithCondition {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private volatile boolean printA = true;

    public void printA() {
        try {
            lock.lock();
            while (printA) {
                /**
                 * 已经在打印A了，就不需要再执行了
                 */
                condition.await();
            }
            printA = true;
            System.out.println("A");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        try {
            lock.lock();
            while (!printA) {
                condition.await();
            }
            printA = false;
            System.out.println("B");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
