package com.demo.eventbus;

/**
 * Created by tang.cheng on 2016/8/17.
 */
public class Event {
    protected String msg;

    public Event(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
