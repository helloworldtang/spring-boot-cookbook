/**
 * @Auther: cheng.tang
 * @Date: 2019/4/1
 * @Description:
 */
package com.tangcheng.learning.date;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Auther: cheng.tang
 * @Date: 2019/4/1
 * @Description:
 */
public class OrderByDateTest {

    @Test
    public void testOrderByDate() {
        List<OrderByDate> tmpList = Arrays.asList(new OrderByDate("2019-03-29 18:41:02", "data1"),
                new OrderByDate("2019-04-01 10:14:02", "data2"),
                new OrderByDate("2019-03-29 19:21:19", "data3"),
                new OrderByDate("2019-03-30 05:41:11", "data4"));
        Stream<OrderByDate> resultDesc = tmpList.stream().sorted(Comparator.comparing(OrderByDate::getTrackTime).reversed());
        assertThat(tmpList.get(0).getRemark()).isEqualTo("data1");
        tmpList.forEach(System.out::println);
        System.out.println("=============Desc=============");
        List<OrderByDate> result = resultDesc.collect(Collectors.toList());
        assertThat(result.get(0).getRemark()).isEqualTo("data2");
        result.forEach(System.out::println);
        System.out.println("=======================");

        Stream<OrderByDate> resultAsc = tmpList.stream().sorted(Comparator.comparing(OrderByDate::getTrackTime));
        assertThat(tmpList.get(0).getRemark()).isEqualTo("data1");
        tmpList.forEach(System.out::println);
        System.out.println("==============Asc==============");
        result = resultAsc.collect(Collectors.toList());
        assertThat(result.get(0).getRemark()).isEqualTo("data2");
        result.forEach(System.out::println);
    }

    @Test
    public void convertJson() {
        Date date = JSON.parseObject("2019-03-29 19:21:19", Date.class);
        System.out.println(date);

        date = JSON.parseObject("2018-12-17T15:17:22", Date.class);
        System.out.println(date);
    }


}
