package com.tangcheng.learning.number;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.assertj.core.api.Assertions.assertThat;


public class NumberCalculateTest {

    /**
     * http://stefanbirkner.github.io/system-rules/index.html
     */
    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    private NumberCalculate numberCalculate;

    @Before
    public void setup() {
        numberCalculate = new NumberCalculate();
    }

    @Test
    public void calculatePercentTest() {
        String percent = numberCalculate.calculatePercent(90, 100);
        String expected = "90";
        assertThat(percent).isEqualTo(expected);
        /**
         String.format()用法
         转换符
         %s: 字符串类型，如："ljq"
         %b: 布尔类型，如：true
         %d: 整数类型(十进制)，如：99
         %f: 浮点类型，如：99.99
         %%: 百分比类型，如：％
         %n: 换行符
         */
        String expectedLog = String.format("num1和num2的百分比为:%s%%%n", expected);
        assertThat(log.getLog()).isEqualToIgnoringCase(expectedLog);
    }

}