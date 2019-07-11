package com.tangcheng.learning.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tangcheng
 * 2017/10/30
 */
public class TwoValidDigitsTest {

    private double source = 123232.12345;


    /**
     * 当前对象大时，当前对象compareTo时，返回1，相等时返回0，小于时返回-1
     */
    @Test
    public void compare() {
        BigDecimal b1 = new BigDecimal("0.5");
        BigDecimal b2 = new BigDecimal("0.8");
        assertThat(b1.compareTo(b2)).isEqualTo(-1);
        BigDecimal zeroOne = new BigDecimal("0");
        assertThat(zeroOne.equals(new BigDecimal("0"))).isTrue();
        BigDecimal zeroThree = new BigDecimal("0.00");
        /**
         * 此处使用equals是false哦
         */
        assertThat(zeroOne.equals(zeroThree)).isFalse();
        assertThat(zeroOne.compareTo(zeroThree)).isEqualTo(0);
        assertThat(zeroOne.compareTo(zeroThree) == 0).isTrue();
    }

    /**
     * 如果对精度有要求
     * 初始化BigDecimal时必须使用String
     */
    @Test
    public void Should_Not_Be_Equal_Between_Init_With_String_And_Init_With_Double() {
        double origin = 0.15;
        BigDecimal originInitWithDouble = new BigDecimal(origin);
        BigDecimal originInitWithStr = new BigDecimal(String.valueOf(origin));
        System.out.println(originInitWithDouble);
        System.out.println(originInitWithStr);
        assertThat(originInitWithStr.compareTo(originInitWithDouble)).isGreaterThan(0);
    }

    @Test
    public void bigDecimalMaxValue() {
        BigDecimal data1 = new BigDecimal("1.2");
        BigDecimal data2 = new BigDecimal("1.5");
        BigDecimal max = data1.max(data2);
        System.out.println(max);
        assertThat(max).isEqualTo(data2);
        max = data2.max(data1);
        System.out.println(max);
        assertThat(max).isEqualTo(data2);
    }

    @Test
    public void m1() {
        /**
         * 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
         * 如果舍弃部分 >= 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同。
         * 注意，这是我们大多数人在小学时就学过的舍入模式(四舍五入)。
         */
        double sourceEgLessThan5 = 123232.12345;
        BigDecimal bd = new BigDecimal(sourceEgLessThan5);
        double actual = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        assertThat(actual).isEqualTo(123232.12);

        sourceEgLessThan5 = 123232.12445;
        bd = new BigDecimal(sourceEgLessThan5);
        actual = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        assertThat(actual).isEqualTo(123232.12);

        sourceEgLessThan5 = 123232.12445;
        bd = new BigDecimal(sourceEgLessThan5);
        actual = bd.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        assertThat(actual).isEqualTo(123232);

        sourceEgLessThan5 = 123232.52445;
        bd = new BigDecimal(sourceEgLessThan5);
        actual = bd.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        assertThat(actual).isEqualTo(123233);

        /**
         * 接近零的舍入模式。
         * 在丢弃某部分之前始终不增加数字(从不对舍弃部分前面的数字加1，即截短)。
         * 注意，此舍入模式始终不会增加计算值的大小。
         */
        sourceEgLessThan5 = 123232.52445;
        bd = new BigDecimal(sourceEgLessThan5);
        actual = bd.setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
        assertThat(actual).isEqualTo(123232);

        /**
         * 接近零的舍入模式。
         * 在丢弃某部分之前始终不增加数字(从不对舍弃部分前面的数字加1，即截短)。
         * 注意，此舍入模式始终不会增加计算值的大小。
         */
        sourceEgLessThan5 = 123232.52445;
        bd = new BigDecimal(sourceEgLessThan5);
        actual = bd.setScale(0, BigDecimal.ROUND_UP).doubleValue();
        assertThat(actual).isEqualTo(123233);

        sourceEgLessThan5 = 123232.12445;
        bd = new BigDecimal(sourceEgLessThan5);
        actual = bd.setScale(0, BigDecimal.ROUND_UP).doubleValue();
        assertThat(actual).isEqualTo(123233);

        double sourceEgEqual5 = 123232.12545;
        bd = new BigDecimal(sourceEgEqual5);
        actual = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        assertThat(actual).isEqualTo(123232.13);

        double sourceEgMoreThan5 = 123232.12645;
        bd = new BigDecimal(sourceEgMoreThan5);
        actual = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        assertThat(actual).isEqualTo(123232.13);
    }

    @Test
    public void m2() {
        DecimalFormat df = new DecimalFormat("#.00");
        String actual = df.format(source);
        assertThat(actual).isEqualTo("123232.12");
    }


    @Test
    public void m3() {
        String actual = String.format("%.2f", source);
        assertThat(actual).isEqualTo("123232.12");
    }


    @Test
    public void m4() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        String actual = nf.format(source);
        assertThat(actual).isEqualTo("123,232.12");
    }


}
