package com.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
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
    private RedisTemplate redisTemplate;

    @Transactional
    public void doBiz() {
        //// TODO: 2016/9/13 事务没有实现
        try {
            BoundListOperations listOps = redisTemplate.boundListOps("myList");
            listOps.leftPush("hello");
            String value = getValue();
            if (value.equals("world")) {
                System.out.println("O,my god");
            }
        } catch (Exception e) {
            RedisSystemException redisSystemException = new RedisSystemException("Fail to doBiz", e);
            throw redisSystemException;
        }
    }

    private String getValue() {
        return null;
    }
}
