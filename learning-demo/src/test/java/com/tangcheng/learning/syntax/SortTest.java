package com.tangcheng.learning.syntax;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/15/2019 11:54 AM
 */
public class SortTest {

    @Test
    public void sortString() {
        String args = "12387921";
        char[] charArray = args.toCharArray();
        System.out.println(charArray);//12387921
        Arrays.sort(charArray);
        System.out.println(charArray);//11223789
        List<char[]> chars = Arrays.asList(charArray);
        assertThat(chars.size()).isEqualTo(1);
        System.out.println(Arrays.toString(charArray));//[1, 1, 2, 2, 3, 7, 8, 9]
        List<Integer> list = Arrays.asList(7, 1, 2, 3, 4, 5);
        Collections.sort(list);//默认升序
        System.out.println(list);//[1, 2, 3, 4, 5, 7]
    }

}
