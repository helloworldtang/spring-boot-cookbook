package com.tangcheng.learning.inject;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by tangcheng on 10/15/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InjectTest {

    @Value("classpath:sourceData.txt")
    private Resource resource;

    @Value("${list.items:1,2,3}")
    private String[] items;

    @Value("${list.items:1,2,3}")
    private Long[] longItems;

    @Value("${list.items:}")
    private String[] itemsWithDefaultBlankValue;

    //@Value("${list.items}")
    private String[] itemsWithoutDefault;

    @Value("${str.value:}")
    private String strWithDefault;

    @Test
    public void injectFileTest() throws IOException {
        assertThat(resource, notNullValue());
        String canonicalPath = resource.getFile().getCanonicalPath();
        System.out.println(canonicalPath);
        List<String> list = IOUtils.readLines(resource.getInputStream(), "utf-8");
        assertThat(list, Matchers.hasItems("123", "456"));
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 使用英文逗点分隔的字符串会被按 英文逗点 进行分隔成 一个数组
     */
    @Test
    public void valueAnnotation_Given_String_Separate_With_Comma_Then_Return_Array() {
        assertThat(items, notNullValue());
        assertThat(items, arrayWithSize(3));
        for (String item : items) {
            System.out.println(item);
        }
        assertThat(items, arrayWithSize(3));
    }

    /**
     * 使用:后，没有给默认值，则只会初始化数组。里面没有东西。
     * 即仅仅 不为null
     */
    @Test
    public void valueAnnotation_Given_Default_With_StringNum_Then_Return_Long_Type_Array() {
        assertThat(longItems, notNullValue());
        assertThat(longItems.length, is(3));
        assertThat(longItems, arrayContaining((long) 1, (long) 2, (long) 3));
    }

    /**
     * 使用:后，没有给默认值，则只会初始化数组。里面没有东西。
     * 即仅仅 不为null
     */
    @Test
    public void valueAnnotation_Given_Default_Is_Nothing_Then_Return_Empty_Array() {
        assertThat(itemsWithDefaultBlankValue, notNullValue());
        assertThat(itemsWithDefaultBlankValue, emptyArray());
        assertThat(itemsWithDefaultBlankValue.length, is(0));
    }


    /**
     * 使用 : 后，会有默认值 空字符串
     */
    @Test
    public void valueAnnotation_String() {
        assertThat(strWithDefault, is(""));
        assertThat(strWithDefault, Matchers.notNullValue());
        System.out.println("strWithDefault:" + strWithDefault + ";");
    }


}
