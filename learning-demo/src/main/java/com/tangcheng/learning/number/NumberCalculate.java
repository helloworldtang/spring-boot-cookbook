package com.tangcheng.learning.number;

import java.text.NumberFormat;

public class NumberCalculate {

    /**
     * 计算百分比
     *
     * @param divisor  除数
     * @param dividend 被除数
     * @return 百分比
     */
    public String calculatePercent(int divisor, int dividend) {
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) divisor / (float) dividend * 100);
        System.out.println("num1和num2的百分比为:" + result + "%");
        return result;
    }

}
