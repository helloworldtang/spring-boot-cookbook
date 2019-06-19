/**
 * @Auther: cheng.tang
 * @Date: 2019/6/19
 * @Description:
 */
package com.tangcheng.learning.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Auther: cheng.tang
 * @Date: 2019/6/19
 * @Description:
 */
public class SplitTest {

    @Test
    public void splitLengthTest() {
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        assertThat(ary.length).isEqualTo(3);
        System.out.println(Arrays.stream(ary).collect(Collectors.toList()));
        System.out.println(Arrays.asList(ary).size());
    }


}
