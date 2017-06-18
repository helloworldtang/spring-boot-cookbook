package com.tangcheng.learning.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng@xiaoyi.com
 * @version : 2017-06-15  13:27
 */
@RunWith(JUnit4.class)
public class ThreadJoinTest {

    @Test
    public void testThreadJoin() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I am running in a separate thread!");
            }
        };
        thread.start();
        thread.join();//如果没有这行代码sout的内容会不能输出
    }


}
