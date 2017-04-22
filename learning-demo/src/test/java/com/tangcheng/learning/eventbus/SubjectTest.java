package com.tangcheng.learning.eventbus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tang.cheng on 2016/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EventBusConfig.class})
public class SubjectTest {

    @Autowired
    private Subject subject;

    @Test
    public void dispatchObserverEvent() throws Exception {
        subject.dispatchObserverEvent("Hello ");
    }

    @Test
    public void dispatchAnotherEvent() throws Exception {
        subject.dispatchAnotherEvent("AnotherEvent ");
    }
}