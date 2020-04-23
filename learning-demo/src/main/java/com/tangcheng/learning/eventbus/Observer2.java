package com.tangcheng.learning.eventbus;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by tang.cheng on 2016/8/17.
 */
@Slf4j
@Component
public class Observer2 {

    @Subscribe
    public void listen(ObserverEvent observerEvent) {
        log.info("type:{}.receive msg:{}", ObserverEvent.class.getSimpleName(), observerEvent.getMsg());
    }


}
