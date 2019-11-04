package com.tangcheng.learning.java8;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: cheng.tang
 * @date: 2019/11/4
 * @see
 * @since
 */
@Slf4j
public class StremTest {


    @Test
    public void streamTest() {
        Integer[] arrays = {1, 2, 3, 4};
        log.info("arrays: {}", JSON.toJSONString(arrays));
        List<Integer> integers = Arrays.asList(arrays);
        log.info("Arrays.asList(arrays).size():{},Arrays.asList(arrays): {}", integers.size(),JSON.toJSONString(integers));

        List<Integer> integerList = Arrays.stream(arrays).collect(Collectors.toList());
        log.info("integerList: {}", JSON.toJSONString(integerList));
        Integer[] object = integerList.toArray(new Integer[0]);
        log.info("integerList.toArray(): {}", JSON.toJSONString(object));
    }


}
