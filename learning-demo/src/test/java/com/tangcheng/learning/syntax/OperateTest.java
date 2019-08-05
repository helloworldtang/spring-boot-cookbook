package com.tangcheng.learning.syntax;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/13/2019 10:15 PM
 */
@Slf4j
public class OperateTest {

    @Test
    public void givenPutNullToMap_whenGetNull_thenReturnTheInputValue() {
        Map<String, String> map = new HashMap<>();
        String expectedValue = "nullString";
        map.put(null, expectedValue);
        String nullToValue = map.get(null);
        assertThat(nullToValue).isEqualTo(expectedValue);

        System.out.println(nullToValue);
        expectedValue = "2nullString";
        map.put(null, expectedValue);
        nullToValue = map.get(null);
        assertThat(nullToValue).isEqualTo(expectedValue);

        System.out.println(nullToValue);
    }

    @Test
    public void givenLeftMoveThenMultiplyAndGivenRightMoveThenDivision() {
        /**
         * java中有三种移位运算符
         *
         * <<      :     左移运算符，num << 1,相当于num乘以2
         *
         * >>      :     右移运算符，num >> 1,相当于num除以2
         *
         * >>>    :     无符号右移，忽略符号位，空位都以0补齐
         */
        int multiplyResult = 1 << 3;
        assertThat(multiplyResult).isEqualTo(8);
        System.out.println(multiplyResult);//8
        int divisionResult = 8 >> 3;
        assertThat(divisionResult).isEqualTo(1);
        System.out.println(divisionResult);//1
    }


}
