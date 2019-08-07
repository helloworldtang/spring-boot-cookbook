package com.tangcheng.app.api.rest.config;

import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by tangcheng on 5/25/2017.
 */

/**
 * The @EnableRedisHttpSession annotation creates a Spring Bean with the name of springSessionRepositoryFilter that implements Filter.
 * The filter is what is in charge of replacing the HttpSession implementation to be backed by Spring Session.
 * In this instance Spring Session is backed by Redis.
 */
@Profile("redis")
@EnableRedisHttpSession
public class SpringSessionConfig {


//    @Bean
//    public FindByIndexNameSessionRepository findByIndexNameSessionRepository(RedisConnectionFactory redisConnectionFactory) {
//        return new RedisOperationsSessionRepository(redisConnectionFactory);
//    }

    /**
     * 注入RedisHttpSessionConfiguration中的defaultRedisSerializer属性，
     * 替换原来jdk默认的serializer
     *
     * @return
     */
//    @Bean("springSessionDefaultRedisSerializer")
//    public GenericFastJsonRedisSerializer redisSessionSerializer() {
//        return new GenericFastJsonRedisSerializer();
//    }


}
