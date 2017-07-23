package com.tangcheng.learning.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CyclicBarrierPrintInOrder {
    private static final int FINAL_NUM = 75;
    private List<PrintTasks> tasks = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();

    public CyclicBarrierPrintInOrder(int nTasks, final int pause) {
        CyclicBarrier barrier = new CyclicBarrier(nTasks, new Runnable() {

            @Override
            public void run() {
                for (PrintTasks task : tasks)
                    if (task.getNum() >= FINAL_NUM) {
                        exec.shutdownNow();
                        return;
                    } else {
                        task.printNum();
                    }
            }
        });
        try {
            TimeUnit.MILLISECONDS.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < nTasks; i++) {
            PrintTasks task = new PrintTasks(barrier);
            tasks.add(task);
            exec.execute(task);
        }
    }

    public static void main(String[] args) {
        new CyclicBarrierPrintInOrder(3, 100);
    }
}

class PrintTasks implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static volatile int num = 0;

    private CyclicBarrier barrier;

    PrintTasks(CyclicBarrier b) {
        barrier = b;
    }

    synchronized int getNum() {
        return num;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                barrier.await();
            }
        } catch (InterruptedException ignored) {
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public void printNum() {
        for (int i = 0; i < 5; i++) {
            System.out.format("%s %3d \n", Thread.currentThread(), ++num);
        }
    }
} 