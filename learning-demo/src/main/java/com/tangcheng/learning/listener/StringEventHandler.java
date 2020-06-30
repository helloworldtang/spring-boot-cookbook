package com.tangcheng.learning.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: cheng.tang
 * @date: 2020/6/30
 * @see
 * @since
 */
@Slf4j
public class StringEventHandler implements EventHandler<String> {
    @Override
    public Boolean support(String msg) {
        log.info("check String support");
        return msg.length() > 0;
    }
}
