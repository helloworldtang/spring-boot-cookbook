package com.tangcheng.learning.number;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2020/12/29 3:25 下午
 * @see
 * @since
 */
public class BigDecimalAndDoubleTest {

    @Test
    public void bigDecimalToDoubleTest() {
        BigDecimal source = new BigDecimal("22.222222");
        Double actual = Double.valueOf(source.toString());
        assertThat(actual.toString()).isEqualTo(source.toString());
    }


}
