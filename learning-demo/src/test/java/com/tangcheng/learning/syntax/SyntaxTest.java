package com.tangcheng.learning.syntax;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-25  15:48
 */
public class SyntaxTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyntaxTest.class);

    @Test
    public void test() {
        System.out.println(true || true && false);//&&的优化级比||高。所以为true
        System.out.println((true || true) && false);//false
        System.out.println(true || (true && false));//true
    }

    @Test
    public void testPriority() {
        /**
         * 用&&连接的两个变量会被当作一个值来看，按从左到右的顺序，
         * 如果&&左边的值为false，则结果已出，&&连接的右边的表达式就不会被执行
         * 这就是短路特性
         * ||运算符的特性:
         * 按顺序进行运算,只要找到一个为真的,就不继续运算,整个结果就返回真
         * &&运算符的特性：
         * 只要找到一个false，就结束
         */
        int flag1 = 1, flag2 = 2;
        LOGGER.info("1、result:{}", checkFalse() && (checkTrue(flag1) || checkTrue(flag2)));//后面两个checkTrue()方法不会被执行
        LOGGER.info("2、result:{}", checkFalse() && checkTrue(flag1) || checkTrue(flag2));//checkTrue(flag1)不会被执行
    }

    private static boolean checkFalse() {
        boolean tmp = false;
        LOGGER.info("I am checkFalse(),I am {}", tmp);
        return tmp;
    }

    private static boolean checkTrue(int flag) {
        boolean tmp = true;
        LOGGER.info("flag:{},I am checkTrue(),I am {}", flag, tmp);
        return tmp;
    }


}
