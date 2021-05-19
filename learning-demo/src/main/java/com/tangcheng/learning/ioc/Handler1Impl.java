package com.tangcheng.learning.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Order(1)
public class Handler1Impl implements Handler {
    @Override
    public void handle() {
        log.info("getCanonicalName:{},getSimpleName:{}", this.getClass().getCanonicalName(), Handler1Impl.class.getSimpleName());
    }
}
