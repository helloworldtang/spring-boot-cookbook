package com.test;

import org.springframework.stereotype.Service;

/**
 * Created by tang.cheng on 2016/11/23.
 */
@Service("test2")
public class TestB implements ITest {
    @Override
    public String getMyName() {
        return TestB.class.getCanonicalName();
    }
}
