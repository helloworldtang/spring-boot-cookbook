package com.tangcheng.learning.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author tangcheng
 * 2017/10/30
 */
public class TwoValidDigitsTest {

    private double source = 123232.12345;

    @Test
    public void m1() {
        double sourceEgLessThan5 = 123232.12345;
        BigDecimal bd = new BigDecimal(sourceEgLessThan5);
        double actual = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        assertThat(actual, is(123232.12));

        double sourceEgEqual5 = 123232.12545;
        bd = new BigDecimal(sourceEgEqual5);
        actual = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        assertThat(actual, is(123232.13));

        double sourceEgMoreThan5 = 123232.12645;
        bd = new BigDecimal(sourceEgMoreThan5);
        actual = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        assertThat(actual, is(123232.13));
    }

    @Test
    public void m2() {
        DecimalFormat df = new DecimalFormat("#.00");
        String actual = df.format(source);
        assertThat(actual, is("123232.12"));
    }


    @Test
    public void m3() {
        String actual = String.format("%.2f", source);
        assertThat(actual, is("123232.12"));
    }


    @Test
    public void m4() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        String actual = nf.format(source);
        assertThat(actual, is("123,232.12"));
    }


}
