package com.tangcheng.learning.concurrent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng@xiaoyi.com
 * @version : 2017-06-15  11:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CalculatorTest {
    @Test
    public void compute() throws Exception {
        long begin = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> result = forkJoinPool.submit(new Calculator(0, 10000));
        System.out.println("cost:" + (System.currentTimeMillis() - begin));
        assertThat(result.get(), is(49995000));
    }

}