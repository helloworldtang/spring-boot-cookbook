package com.tangcheng.learning.validation.method.service.impl;

import com.tangcheng.learning.validation.method.service.MethodValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: cheng.tang
 * @date: 2020/3/10
 * @see
 * @since
 */
@Service
@Slf4j
public class MethodValidationServiceImpl implements MethodValidationService {


    @Override
    public String process(Integer id, String name) {
        log.info("id:{} name:{}", id, name);
        return "success";
    }


}
