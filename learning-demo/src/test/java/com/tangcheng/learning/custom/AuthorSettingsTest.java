package com.tangcheng.learning.custom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by MyWorld on 2016/8/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SettingsConfig.class})//读的是Application.properties文件中的数据
public class AuthorSettingsTest {
    @Autowired
    private AuthorSettings authorSettings;

    @Test
    public void initTest() {
        Long age = authorSettings.getAge();
        String name = authorSettings.getName();
        assertEquals("learning-demo", name);
        assertEquals(32, age.longValue());
    }
}