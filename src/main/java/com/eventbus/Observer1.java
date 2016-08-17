package com.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by tang.cheng on 2016/8/17.
 */
@Component
public class Observer1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Observer1.class);
    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void register() {
        eventBus.register(this);
    }

    @Subscribe
    public void listen(ObserverEvent observerEvent) {
        LOGGER.info("type:{}.receive msg:{}", ObserverEvent.class.getSimpleName(), observerEvent.getMsg());
    }


    @Subscribe
    public void listen(AnotherEvent observerEvent) {
        LOGGER.info("type:{}.receive msg:{}", AnotherEvent.class.getSimpleName(), observerEvent.getMsg());
    }
}
