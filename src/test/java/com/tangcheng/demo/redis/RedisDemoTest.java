package com.tangcheng.demo.redis;

import com.tangcheng.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by MyWorld on 2016/9/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class})
public class RedisDemoTest {
    @Autowired
    private RedisDemo redisDemo;

    @Test
    public void doBiz() throws Exception {
        redisDemo.doBiz();
    }

}