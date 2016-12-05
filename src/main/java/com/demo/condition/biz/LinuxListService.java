package com.demo.condition.biz;

/**
 * Created by tang.cheng on 2016/11/25.
 */
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
