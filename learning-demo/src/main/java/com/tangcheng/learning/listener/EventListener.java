package com.tangcheng.learning.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: cheng.tang
 * @date: 2020/6/30
 * @see
 * @since
 */
public class EventListener {

    /**
     * 会抛异常
     * java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
     *
     * @param args
     */
    public static void main(String[] args) {
        List<EventHandler> eventHandlerList = new ArrayList<>();
        eventHandlerList.add(new StringEventHandler());
        eventHandlerList.add(new IntegerEventHandler());
        for (EventHandler eventHandler : eventHandlerList) {
            eventHandler.support("测试");
        }
    }

}
