package com.eventbus;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tang.cheng on 2016/8/17.
 */
@Component
public class Subject {
    @Autowired
    private EventBus eventBus;

    public void dispatchObserverEvent(String msg) {
        eventBus.post(new ObserverEvent(msg));
    }

    public void dispatchAnotherEvent(String msg) {
        eventBus.post(new AnotherEvent(msg));
    }
}
