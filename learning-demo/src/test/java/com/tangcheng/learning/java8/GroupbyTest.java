package com.tangcheng.learning.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingInt;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 9/29/2018 8:44 AM
 */
public class GroupbyTest {

    @Test
    public void test() {
        List<String> strings = Arrays.asList("a", "bb", "cc", "ddd");
        Map<Integer, IntSummaryStatistics> result = strings.stream()
                .collect(groupingBy(String::length, summarizingInt(String::hashCode)));
        System.out.println(result);
    }

}
