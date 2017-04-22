package com.tangcheng.learning.custom;

import com.tangcheng.CookbookApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by MyWorld on 2016/8/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CookbookApplication.class)
public class ThreadSettingsTest {

    @Autowired
    private ThreadSettings threadSettings;

    /**
     * Caused by: org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 1 errors
     * Field error in object 'thread' on field 'count': rejected value [1000]; codes [Max.thread.count,Max.count,Max.int,Max]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [thread.count,count]; arguments []; default message [count],999]; default message [最大不能超过999]
     * at org.springframework.boot.bind.PropertiesConfigurationFactory.validate(PropertiesConfigurationFactory.java:296)
     * at org.springframework.boot.bind.PropertiesConfigurationFactory.doBindPropertiesToTarget(PropertiesConfigurationFactory.java:255)
     * at org.springframework.boot.bind.PropertiesConfigurationFactory.bindPropertiesToTarget(PropertiesConfigurationFactory.java:227)
     * at org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor.postProcessBeforeInitialization(ConfigurationPropertiesBindingPostProcessor.java:296)
     */
//    @Test(expected = BindException.class) //这个单元测试不能用，因为Spring会启动不了：java.lang.IllegalStateException: Failed to load ApplicationContext。
    @Test
    public void initFromProperty() {
        int actual = threadSettings.getCount();
        int expected = 1000;
        assertThat(expected, is(actual));
    }
}