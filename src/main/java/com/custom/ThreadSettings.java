package com.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by MyWorld on 2016/8/9.
 */
@Component
@ConfigurationProperties(prefix = "thread", locations = {"classpath:config/thread.properties"})
public class ThreadSettings {
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
