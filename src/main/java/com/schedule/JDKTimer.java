package com.schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 一个Timer中多个schedule之间是相互干扰的
 */
public class JDKTimer {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd  HH:mm:ss SSS");

    public static void main(String[] args) throws InterruptedException {
        final Timer timer = new Timer();
        executeAtFixDelay(timer);
        executeAtFixPeriod(timer);
        atFixedDelayFixedPeriod(timer);
        executeAtFixedTimeAndCancel(timer);
    }

    private static void atFixedDelayFixedPeriod(Timer timer) {
        System.out.println(getCurrentDate() + " FixedDelayFixedPeriod delay 2s,period 3s.Command cost 10s");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println(getCurrentDate() + " FixedDelayFixedPeriod execute ...");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2000, 3000);
    }

    private static void executeAtFixDelay(Timer timer) {
        int delay = 10 * 1000;
        String name = " fixed delay ";
        System.out.println(getCurrentDate() + name + " After " + delay + "ms to execute  ");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(getCurrentDate() + " fixed delay executing!");
            }
        }, delay);
    }

    private static void executeAtFixPeriod(Timer timer) {
        System.out.println(getCurrentDate() + " fixed Rate 1s ");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(getCurrentDate() + " fixed Rate  executing");
            }
        }, new Date(), 1000);
    }

    private static void executeAtFixedTimeAndCancel(final Timer timer) {
        System.out.println(getCurrentDate() + " After " + 2 + " MINUTE to complete and cancel timer");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 2);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
            }
        }, calendar.getTime());
    }

    public static String getCurrentDate() {
        return DATE_FORMAT.format(new Date());
    }

}
