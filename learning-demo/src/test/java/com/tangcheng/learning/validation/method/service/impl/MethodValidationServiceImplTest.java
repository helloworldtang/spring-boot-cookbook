package com.tangcheng.learning.validation.method.service.impl;

import com.tangcheng.learning.validation.method.config.MethodValidationConfig;
import com.tangcheng.learning.validation.method.service.MethodValidationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;

/**
 * @author: cheng.tang
 * @date: 2020/3/10
 * @see
 * @since
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MethodValidationConfig.class})
public class MethodValidationServiceImplTest {

    @Autowired
    private MethodValidationService methodValidationService;

    @Test(expected = ConstraintViolationException.class)
    public void process() {
        log.info("{}", methodValidationService.getClass());
        try {
            String result = methodValidationService.process(-1, null);
            log.info("result:{}", result);
        } catch (Exception e) {
            log.warn("msg:{}", e.getMessage(), e);
            throw e;
        }
    }


}