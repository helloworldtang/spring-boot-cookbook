package com.tangcheng.learning.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MyWorld on 2016/9/13.
 */
@Service
public class RedisDemo {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Transactional(rollbackFor = {NullPointerException.class})
    public void doBiz() {
        BoundListOperations<Object, Object> listOps = redisTemplate.boundListOps("myList");
        listOps.leftPush("hello");
        listOps.leftPush("world");
        String value = getValue();
        if (value.equals("world")) {
            System.out.println("O,my god");
        }

    }

    private String getValue() {
        return null;
    }
}
