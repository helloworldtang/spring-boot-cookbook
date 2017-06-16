package com.tangcheng.learning.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng@xiaoyi.com
 * @version : 2017-06-16  17:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testTypedTuple() {

        Set<DefaultTypedTuple<Integer>> typedTupleSet = new HashSet<>();
        ArrayList<Integer> source = newArrayList(1, 2, 3, 4, 5, 6);
        for (int i = 0; i < source.size(); i++) {
            DefaultTypedTuple<Integer> typedTuple = new DefaultTypedTuple<>(source.get(i), i + 100.0);
            typedTupleSet.add(typedTuple);
        }
        BoundZSetOperations myzset = redisTemplate.boundZSetOps("myzset");
        myzset.add(typedTupleSet);
        System.out.println(myzset.range(0, -1));
    }


}
