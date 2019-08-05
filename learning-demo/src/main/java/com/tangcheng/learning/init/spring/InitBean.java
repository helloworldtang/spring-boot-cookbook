package com.tangcheng.learning.init.spring;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/6/2019 10:57 AM
 */
@Slf4j
public class InitBean {

    public InitBean() {
        log.info("Step1:begin to execute Constructor Function");
    }

    public void initMethod() {
        log.info("Step3:begin to execute initMethod");
    }


    @PostConstruct
    public void postConstructMethod1() {
        log.info("Step2:begin to execute  postConstructMethod1 with @PostConstruct");
    }

    @PostConstruct
    public void postConstructMethod2() {
        log.info("Step2:begin to execute postConstructMethod2 with @PostConstruct");
    }

}
