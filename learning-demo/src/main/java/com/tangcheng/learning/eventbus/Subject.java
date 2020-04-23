package com.tangcheng.learning.eventbus;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by tang.cheng on 2016/8/17.
 */
@Component
public class Subject {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private Observer1 observer1;

    @Autowired
    private Observer2 observer2;


    @PostConstruct
    public void register() {
        eventBus.register(observer1);
        eventBus.register(observer2);
    }


    public void dispatchObserverEvent(String msg) {
        eventBus.post(new ObserverEvent(msg));
    }

    public void dispatchAnotherEvent(String msg) {
        eventBus.post(new AnotherEvent(msg));
    }


}
