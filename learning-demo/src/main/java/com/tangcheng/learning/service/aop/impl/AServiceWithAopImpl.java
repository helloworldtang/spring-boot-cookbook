package com.tangcheng.learning.service.aop.impl;

import com.tangcheng.learning.service.aop.AServiceWithAop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 10/5/2018 12:55 PM
 */
@Service
@Slf4j
@Transactional
public class AServiceWithAopImpl implements AServiceWithAop {

    @Transactional
    @Override
    public void doBiz() {
        log.info("a finished");
    }

}
