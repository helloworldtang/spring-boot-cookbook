package com.tangcheng.learning.concurrent.conidtion;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class PrintWithConditionTest {

    @Test
    public void testPrintWithCondition() throws InterruptedException {
        CountDownLatch countLatch = new CountDownLatch(2);
        PrintWithCondition printWithCondition = new PrintWithCondition();
        Thread printAThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    printWithCondition.printA();
                }
                countLatch.countDown();

            }
        });

        Thread printBThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    printWithCondition.printB();
                }
                countLatch.countDown();
            }
        });
        printAThread.start();
        printBThread.start();
        countLatch.await();
    }


}