package com.tangcheng.learning.oo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-06  13:28
 */
public class CircleTest {


    @Test
    public void moveCircle() throws Exception {
        Circle circle = new Circle(10, 10);
        circle.print(circle);
        circle.moveCircle(circle, 20, 20);
        circle.print(circle);
    }

    /**
     * 虽然是一个变更名，但指针地址已经发生变化
     */
    @Test
    public void testParameterValue() {
        BigDecimal totalFee = BigDecimal.ZERO;
        BigDecimal discount = new BigDecimal("20.2");
        totalFee = totalFee.add(discount);
        OrderItem orderItem = new OrderItem();
        orderItem.setDiscount(totalFee);
        BigDecimal orderInquiry = new BigDecimal("99.9");
        totalFee = totalFee.add(orderInquiry);
        orderItem.setTotalFee(totalFee);
        System.out.println(JSON.toJSONString(orderItem));
        assertThat(orderItem.getDiscount().compareTo(discount)).isEqualTo(0);
        assertThat(orderItem.getTotalFee().compareTo(discount.add(orderInquiry))).isEqualTo(0);
    }

    @Data
    public static class OrderItem {
        private BigDecimal discount;
        private BigDecimal totalFee;
    }


}