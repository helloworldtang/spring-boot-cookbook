package com.tangcheng.learning.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Created by tang.cheng on 2017/5/27.
 */
public class AddSubtractMultiplyDivide {

    public static void main(String[] args) {
        String q1 = "0.06 + 0.01 =";
        String q2 = "1.0 - 0.42=";
        String q3 = "4.015 * 100=";
        String q4 = "303.1 / 1000=";
        NumberFormat percent = NumberFormat.getPercentInstance();     //建立百分比格式化用
        percent.setMaximumFractionDigits(5);
        double v = 0.06 + 0.01;
        System.out.println(q1 + v + "==" + percent.format(v));
        System.out.println(q2 + (1.0 - 0.42));
        System.out.println(q3 + (4.015 * 100));
        System.out.println(q4 + (303.1 / 1000));
        System.out.println("====================LINE====================");


        //****BigDecimal中传入的double类型的数据，要为String类型，不然得到在BigDecimal仍然是不准确的double数据****
//        BigDecimal addend = new BigDecimal(0.06);
//        BigDecimal augend = new BigDecimal(0.01);
        BigDecimal addend = new BigDecimal("0.06");
        BigDecimal augend = new BigDecimal("0.01");
        BigDecimal result = addend.add(augend);
        System.out.println(q1 + result.doubleValue() + "==" + percent.format(v));

//        BigDecimal minuend = new BigDecimal(1.0);
//        BigDecimal subtrahend = new BigDecimal(0.42);
        BigDecimal minuend = new BigDecimal("1.0");
        BigDecimal subtrahend = new BigDecimal("0.42");
        result = minuend.subtract(subtrahend);
        System.out.println(q2 + result.doubleValue());

//        BigDecimal multiplicand = new BigDecimal(4.015);
//        BigDecimal multiplicator = new BigDecimal(100);
        BigDecimal multiplicand = new BigDecimal("4.015");
        BigDecimal multiplicator = new BigDecimal(100);
        result = multiplicand.multiply(multiplicator);
        System.out.println(q3 + result.doubleValue());

//        BigDecimal dividend = new BigDecimal(303.1);
        BigDecimal dividend = new BigDecimal("303.1");
        BigDecimal divisor = new BigDecimal(1000);
        int scale = 10;
        result = dividend.divide(divisor, scale, RoundingMode.HALF_UP);
        System.out.println(q4 + result.doubleValue());

    }

}