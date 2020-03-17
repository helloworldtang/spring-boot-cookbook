package com.tangcheng.learning.java8;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: cheng.tang
 * @date: 2020/3/17
 * @see
 * @since
 */
public class TestStreamApi {


    public static final int INITIAL_CAPACITY = 10;

    @Test
    public void changeDataTypeAndCollectWithINITIAL_CAPACITY() {
        List<Product> productList = new ArrayList<>(INITIAL_CAPACITY);
        List<String> strIdList = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            Product product = new Product();
            product.setId(i);
            productList.add(product);
            strIdList.add(String.valueOf(i));
        }

        List<String> productIdStrList = productList.stream().map(product -> product.getId().toString()).collect(Collectors.toCollection(() -> new ArrayList<>(INITIAL_CAPACITY)));
        System.out.println(JSON.toJSONString(productIdStrList));
        assertThat(productIdStrList).containsAll(strIdList);
    }



}