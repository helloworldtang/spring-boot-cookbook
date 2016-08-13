package com.schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

/**
 * Created by MyWorld on 2016/8/13.
 */
public class JDKTimer {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd  HH:mm:ss SSS");

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();

        CountDownLatch countDownLatch = new CountDownLatch(2);
        int delay = 10 * 1000;
        String name = "Scheduler1";
        System.out.println(getCurrentDate() + " After " + delay + "ms to execute  " + name);
        timer.schedule(new TimerTaskWork(countDownLatch, name), delay);

        name = "Scheduler2";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 12);
        System.out.println(getCurrentDate() + " After " + 12 + "s to execute  " + name);
        timer.schedule(new TimerTaskWork(countDownLatch, name), calendar.getTime());
        countDownLatch.await();
        timer.cancel();
        System.out.println(getCurrentDate() + "  finish!");

        System.out.println(getCurrentDate() + " fixRate 10s ");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(getCurrentDate() + " fix rate  executing");
            }
        }, new Date(), 10000);

    }

    public static String getCurrentDate() {
        return DATE_FORMAT.format(new Date());
    }

    private static class TimerTaskWork extends TimerTask {

        private final CountDownLatch countDownLatch;
        private final String name;

        public TimerTaskWork(CountDownLatch countDownLatch, String name) {
            this.countDownLatch = countDownLatch;
            this.name = name;
        }

        @Override
        public void run() {
            this.countDownLatch.countDown();
            System.out.println(getCurrentDate() + " " + name + " executing!");
        }
    }

}
