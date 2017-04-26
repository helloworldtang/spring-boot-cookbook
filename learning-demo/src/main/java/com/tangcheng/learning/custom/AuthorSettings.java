package com.tangcheng.learning.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;

@Component
@Validated
@ConfigurationProperties(prefix = "info.author")
public class AuthorSettings {
    private String name;
    /**
     * 如果info.author.age=1000，则会报错：
     * Caused by: org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 1 errors
     * Field error in object 'thread' on field 'count': rejected value [1000]; codes [Max.thread.count,Max.count,Max.int,Max];
     * arguments [org.springframework.context.support.DefaultMessageSourceResolvable:
     * codes [thread.count,count]; arguments []; default message [count],999]; default message [最大不能超过999]
     * at org.springframework.boot.bind.PropertiesConfigurationFactory.validate(PropertiesConfigurationFactory.java:296)
     */
    @Max(1000)
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}