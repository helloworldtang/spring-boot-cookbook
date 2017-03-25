package com.tangcheng.demo.schedule.quartz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by MyWorld on 2016/9/12.
 */
@Service
public class BusinessService implements IBusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);

    @Override
    public void doBusiness() {
        LOGGER.info("do business!");
    }
}
