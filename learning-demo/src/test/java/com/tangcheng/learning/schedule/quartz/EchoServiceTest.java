package com.tangcheng.learning.schedule.quartz;

import com.tangcheng.learning.schedule.quartz.config.QuartzConfig;
import com.tangcheng.learning.schedule.quartz.service.EchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by MyWorld on 2016/9/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QuartzConfig.class})
public class EchoServiceTest {
    @Autowired
    private EchoService echoService;

    @Test
    public void start() throws Exception {
        echoService.start();
        TimeUnit.MINUTES.sleep(1);
    }

}