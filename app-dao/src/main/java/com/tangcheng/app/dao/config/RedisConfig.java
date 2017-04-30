package com.tangcheng.app.dao.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by MyWorld on 2016/9/13.
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return objectMapper;
    }

    /**
     * Transaction Support is disabled by default and has to be explicitly enabled for each RedisTemplate in use by setting setEnableTransactionSupport(true).
     * This will force binding the RedisConnection in use to the current Thread triggering MULTI.
     * If the transaction finishes without errors, EXEC is called, otherwise DISCARD.
     * Once in MULTI, RedisConnection would queue write operations,
     * all readonly operations, such as KEYS are piped to a fresh (non thread bound) RedisConnection.
     *
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        /**
         * redis的事务是基于数据库事务的
         */
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }


}
