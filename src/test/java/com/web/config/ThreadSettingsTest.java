package com.web.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by MyWorld on 2016/8/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SettingsConfig.class})
public class ThreadSettingsTest {

    @Autowired
    private ThreadSettings threadSettings;
    @Test
    public void initFromProperty() {
        int actual = threadSettings.getCount();
        int expected = 1000;
        assertEquals(expected,actual);
    }
}