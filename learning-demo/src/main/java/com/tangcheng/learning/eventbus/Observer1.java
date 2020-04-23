package com.tangcheng.learning.eventbus;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by tang.cheng on 2016/8/17.
 */
@Slf4j
@Component
public class Observer1 {

    /**
     * 如果一个Subscriber报错了，并不影响其它的
     * https://www.cnblogs.com/softidea/p/5731234.html
     *
     * @param observerEvent
     */
    @Subscribe
    public void listen(ObserverEvent observerEvent) {
//        log.info("type:{}.receive msg:{}", ObserverEvent.class.getSimpleName(), observerEvent.getMsg());
        throw new IllegalArgumentException("Observer1报错了");
    }


    @Subscribe
    public void listen(AnotherEvent observerEvent) {
        log.info("type:{}.receive msg:{}", AnotherEvent.class.getSimpleName(), observerEvent.getMsg());
    }


}
