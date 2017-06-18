package com.tangcheng.learning.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveTask;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng@xiaoyi.com
 * @version : 2017-06-15  11:32
 */
public class Calculator extends RecursiveTask<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

    private static final int THRESHOLD = 100;
    private int start;
    private int end;

    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        if ((start - end) < THRESHOLD) {
//        if ((end - start) < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
            LOGGER.info("tmp sum:{}", sum);
        } else {
            int middle = (start + end) / 2;
            Calculator left = new Calculator(start, middle);
            Calculator right = new Calculator(middle + 1, end);
            left.fork();
            right.fork();
            Integer leftJoin = left.join();
            Integer rightJoin = right.join();
            LOGGER.info("left.join:{},right.join:{}", leftJoin, rightJoin);
            sum = leftJoin + rightJoin;
        }
        return sum;
    }
}
