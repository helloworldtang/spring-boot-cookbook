package com.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by MyWorld on 2016/8/13.
 */
public class FixedRateFixedDelay {

    public void fixedDelay() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);
        long initialDelay = 1;
        long delay = 4;
        final ScheduledFuture<?> scheduledFuture = scheduler.scheduleWithFixedDelay(new Task("FixedDelay"), initialDelay, delay, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledFuture.cancel(true);
                scheduler.shutdown();
            }
        }, 2, TimeUnit.MINUTES);
    }

    public void fixedRate() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);
        long initialDelay = 1;
        long delay = 4;
        final ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(new Task("FixedRate"), initialDelay, delay, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledFuture.cancel(true);
                scheduler.shutdown();
            }
        }, 2, TimeUnit.MINUTES);
    }

    private class Task implements Runnable {
        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(JDKTimer.getCurrentDate() + "  " + name + "  start ");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        FixedRateFixedDelay fixedRateFixedDelay = new FixedRateFixedDelay();
        fixedRateFixedDelay.fixedRate();
        fixedRateFixedDelay.fixedDelay();
    }


}
