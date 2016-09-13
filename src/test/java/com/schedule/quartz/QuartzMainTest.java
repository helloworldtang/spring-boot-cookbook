package com.schedule.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by MyWorld on 2016/9/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QuartzConfig.class})
public class QuartzMainTest {
    @Autowired
    private QuartzMain quartzMain;

    @Test
    public void start() throws Exception {
        quartzMain.start();
        TimeUnit.MINUTES.sleep(1);
    }

}