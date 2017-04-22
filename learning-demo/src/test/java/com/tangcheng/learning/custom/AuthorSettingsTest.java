package com.tangcheng.learning.custom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by MyWorld on 2016/8/12.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {SettingsConfig.class})
public class AuthorSettingsTest {
    @Autowired
    private AuthorSettings authorSettings;

    @Test
    public void initTest() {
        Long age = authorSettings.getAge();
        String name = authorSettings.getName();
        assertEquals("Spring Boot", name);
        assertEquals(32, age.longValue());
    }
}