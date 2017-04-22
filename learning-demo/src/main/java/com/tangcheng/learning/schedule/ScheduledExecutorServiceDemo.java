package com.tangcheng.learning.schedule;

import java.util.concurrent.*;

/**
 * Created by MyWorld on 2016/8/13.
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(10);
        System.out.println(JDKTimer.getCurrentDate() + "   begin ....");
        schedule.schedule(() -> {
            try {
                System.out.println(JDKTimer.getCurrentDate() + "  Worker1  executing !");
                TimeUnit.SECONDS.sleep(20);//Will not affect the execution of the next
                System.out.println(JDKTimer.getCurrentDate() + " After sleep.  Worker1  executing !");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, 10, TimeUnit.SECONDS);

        schedule.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(JDKTimer.getCurrentDate() + "  Worker2  executing !");
            }
        }, 10, TimeUnit.SECONDS);

        final String workerName = "  Worker3 ";
        final ScheduledFuture<?> scheduledFuture = schedule.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(JDKTimer.getCurrentDate() + workerName + " executing !");
            }
        }, 0, 10, TimeUnit.SECONDS);

        schedule.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledFuture.cancel(true);
                schedule.shutdown();
            }
        }, 3, TimeUnit.MINUTES);
    }
}
