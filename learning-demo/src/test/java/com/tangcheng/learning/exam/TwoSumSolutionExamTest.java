package com.tangcheng.learning.exam;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2021/6/4 10:02 上午
 * @see
 * @since
 */
public class TwoSumSolutionExamTest {

    @Test
    public void twoSumGive_When_10_Then_6_4() {
        int[] expect = {6, 4};
        TwoSumSolutionExam twoSumSolutionExam = new TwoSumSolutionExam();
        int[] actual = twoSumSolutionExam.twoSumFindFirst(new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 10);
        assertArrayEquals(expect, actual);
    }

    @Test
    public void twoSumGive_When_100_Then_null() {
        TwoSumSolutionExam twoSumSolutionExam = new TwoSumSolutionExam();
        int[] actual = twoSumSolutionExam.twoSumFindFirst(new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 100);
        assertNull(actual);
    }


}