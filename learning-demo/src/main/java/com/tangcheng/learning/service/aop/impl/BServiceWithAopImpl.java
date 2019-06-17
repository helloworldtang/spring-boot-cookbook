package com.tangcheng.learning.service.aop.impl;

import com.tangcheng.learning.service.aop.BServiceWithAop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 10/5/2018 12:57 PM
 */
@Service
@Slf4j
@Transactional
public class BServiceWithAopImpl implements BServiceWithAop {

    @Autowired
    private AServiceWithAopImpl aServiceWithAop;

    @Override
    public void doBiz() {
        aServiceWithAop.doBiz();
        log.info("b finished");
    }


}
