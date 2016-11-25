package com.condition;

import com.condition.biz.ListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by tang.cheng on 2016/11/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConditionConfig.class})
public class ListServiceTest {
    @Autowired
    private ListService listService;

    @Test
    public void showListCmd() throws Exception {
        String listCmd = listService.showListCmd();
        System.out.println(listCmd);
        assertThat(listCmd, is("dir"));
    }

}