package com.tangcheng.learning.oo;

import org.junit.Test;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-06  13:28
 */
public class CircleTest {
    @Test
    public void moveCircle() throws Exception {
        Circle circle=new Circle(10,10);
        circle.print(circle);
        circle.moveCircle(circle, 20, 20);
        circle.print(circle);
    }

}