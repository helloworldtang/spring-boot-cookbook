package com.tangcheng.demo.condition.biz;

/**
 * Created by tang.cheng on 2016/11/25.
 */
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
