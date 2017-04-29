package com.tangcheng.app.schedule.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by tangcheng on 4/30/2017.
 */
@Repository
public class EchoBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoBiz.class);

    public void echo(Integer value) {
        LOGGER.info("==echo {}==", value);
    }

    public void echo(String value) {
        LOGGER.info("==echo {}==", value);
    }

}
