package com.tangcheng.learning.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <br>折扣服务</br>
 */
@Slf4j
public class Discount {
    /**
     * double d = 3.1415926;
     * String result = String .format("%.2f");
     */
    private static final DecimalFormat df = new java.text.DecimalFormat("#.00");

    public static String applyDiscount(Quote quote) {
        log.info("Discount applyDiscount quote:{}", JSON.toJSONString(quote));
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        //return df.format(price * (100 - code.percentage) / 100);
        return new BigDecimal(price * (100 - code.percentage) / 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //枚举类型定义的折扣代码
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }


}
