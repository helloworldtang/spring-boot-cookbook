package com.tangcheng.learning.schedule.quartz.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by MyWorld on 2016/9/12.
 */
@Service
public class EchoBiz implements IEchoBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoBiz.class);

    @Override
    public void doBusiness() {
        LOGGER.info("do business!");
    }
}
