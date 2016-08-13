package com.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by MyWorld on 2016/8/13.
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(10);
        System.out.println(JDKTimer.getCurrentDate() + "   begin ....");
        schedule.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(JDKTimer.getCurrentDate() + "  Worker1  executing !");
                    TimeUnit.SECONDS.sleep(20);//Will not affect the execution of the next
                    System.out.println(JDKTimer.getCurrentDate() + " After sleep.  Worker1  executing !");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, 10, TimeUnit.SECONDS);

        schedule.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(JDKTimer.getCurrentDate() + "  Worker2  executing !");
            }
        }, 10, TimeUnit.SECONDS);

        schedule.shutdown();

    }
}
