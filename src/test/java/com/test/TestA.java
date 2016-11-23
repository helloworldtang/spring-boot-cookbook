package com.test;

import org.springframework.stereotype.Service;

/**
 * Created by tang.cheng on 2016/11/23.
 */
@Service
public class TestA implements ITest{
    @Override
    public String getMyName() {
        return TestA.class.getCanonicalName();
    }
}
