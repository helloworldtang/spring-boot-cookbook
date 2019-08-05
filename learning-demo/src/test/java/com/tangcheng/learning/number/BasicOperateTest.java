package com.tangcheng.learning.number;

import org.junit.Test;

/**
 * @author tangcheng
 * 2017/10/30
 */
public class BasicOperateTest {
    @Test
    public void operateTest() {

        /**
         * 取余运算符是%是一个双目运算符，它的操作数通常是正整数也可以是负数甚至是浮点数，如果负数参与此运算，则结果的正负取决于前面一个数是整数还是负数(%)
         * 取整从字面意思理解就是被除数到底包含几个除数，也就是能被整除多少次 (/)
         * http://blog.csdn.net/u011514810/article/details/52754157
         */
        System.out.println(6 % 2);//0
        System.out.println(6 / 2);//3

        System.out.println(4 % 2);//0
        System.out.println(4 / 2);//2

        System.out.println(5 % 2);//1
        System.out.println(5 / 2);//2
    }

}
