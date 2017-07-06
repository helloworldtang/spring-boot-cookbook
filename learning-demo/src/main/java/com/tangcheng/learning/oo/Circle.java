package com.tangcheng.learning.oo;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-06  13:16
 */
public class Circle {
    private int x;
    private int y;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveCircle(Circle circle, int deltaX, int deltaY) {
        // code to move origin of circle to x+deltaX, y+deltaY
        circle.setX(circle.getX() + deltaX);
        circle.setY(circle.getY() + deltaY);

        // http://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html
        // code to assign a new reference to circle
        circle = new Circle(0, 0);//自这行后，moveCircle方法就丢失入参circle---即不能再操作入参circle。就像连续对一个primitive 变量赋值，后一次操作会覆盖前面的
        print(circle);
    }

    public void print(Circle circle) {
        System.out.println("moveCircle:" + circle + ",hash code:" + circle.hashCode());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
