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

    /**
     * 使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的
     * add/remove/clear 方法会抛出 UnsupportedOperationException 异常。
     * 说明：asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。Arrays.asList 体现的
     * 是适配器模式，只是转换接口，后台的数据仍是数组。
     * String[] str = new String[] { "you", "wu" }; List list = Arrays.asList(str);
     * 第一种情况：list.add("yangguanbao"); 运行时异常。
     * 第二种情况：str[0] = "gujin"; 那么 list.get(0)也会随之修改。
     */
    @Test
    public void splitLengthTest() {
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        assertThat(ary.length).isEqualTo(3);
        System.out.println(Arrays.stream(ary).collect(Collectors.toList()));
        System.out.println(Arrays.asList(ary).size());
    }


}
