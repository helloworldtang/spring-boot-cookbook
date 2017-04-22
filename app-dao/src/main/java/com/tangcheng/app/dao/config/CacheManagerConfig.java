package com.tangcheng.app.dao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by tangcheng on 4/15/2017.
 */
@Configuration
@EnableCaching
public class CacheManagerConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(12 * 60 * 60);
        return cacheManager;
    }
}
