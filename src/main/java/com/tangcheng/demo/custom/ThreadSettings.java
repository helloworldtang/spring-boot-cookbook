package com.tangcheng.demo.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;

/**
 * Created by MyWorld on 2016/8/9.
 */
@Component
@ConfigurationProperties(prefix = "thread", locations = {"classpath:config/thread.properties"})
public class ThreadSettings {


    /**
     * 如果thread.count=1000，则会报错：
     * Caused by: org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 1 errors
     * Field error in object 'thread' on field 'count': rejected value [1000]; codes [Max.thread.count,Max.count,Max.int,Max]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [thread.count,count]; arguments []; default message [count],999]; default message [最大不能超过999]
     * at org.springframework.boot.bind.PropertiesConfigurationFactory.validate(PropertiesConfigurationFactory.java:296)
     */
    @Max(1000)
    Integer count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
