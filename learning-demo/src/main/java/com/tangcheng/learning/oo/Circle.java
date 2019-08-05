package com.tangcheng.learning.oo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-06  13:16
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Circle {
    private Integer x;
    private Integer y;

    public void moveCircle(Circle circle, int deltaX, int deltaY) {
        // code to move origin of circle to x+deltaX, y+deltaY
        circle.setX(circle.getX() + deltaX);
        circle.setY(circle.getY() + deltaY);

        // http://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html
        // code to assign a new reference to circle
        /**
         * 自这行后，moveCircle方法就丢失入参circle，即不能再操作入参circle。因为丧失了引用。其它对此对象有引用的地方，仍可以操作此对象
         * 但这个入参仍然存在。因为相关引用仍存在
         * 就像连续对一个primitive 变量赋值，后一次操作会覆盖前面的
         */
        circle = new Circle(0, 0);
        print(circle);
    }

    public void print(Circle circle) {
        System.out.println("moveCircle:" + circle + ",hash code:" + circle.hashCode());
    }

}
