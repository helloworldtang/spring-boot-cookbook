package com.tangcheng.learning.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StreamTest {

    @Test
    public void joinList() {
        String expect = "1,2,3,4,5";
        List<String> source = Arrays.asList("1", "2", "3", "4", "5");
        String actual = source.stream().collect(Collectors.joining(","));
        assertThat(actual, is(expect));
    }

}
