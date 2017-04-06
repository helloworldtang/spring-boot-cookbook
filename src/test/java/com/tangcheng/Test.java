package com.tangcheng;

import tk.mybatis.mapper.provider.SpecialProvider;

/**
 * Created by tang.cheng on 2017/4/6.
 */
public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        SpecialProvider.class.newInstance();
    }
}
