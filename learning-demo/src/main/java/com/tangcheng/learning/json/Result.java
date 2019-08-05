package com.tangcheng.learning.json;

import java.util.List;

public class Result<T> {

    private String msg;

    private List<T> module;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getModule() {
        return module;
    }

    public void setModule(List<T> module) {
        this.module = module;
    }


    @Override
    public String toString() {
        return "Result{" +
                "msg='" + msg + '\'' +
                ", module=" + module +
                '}';
    }
}