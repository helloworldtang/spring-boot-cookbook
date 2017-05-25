package com.tangcheng.app.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by tangcheng on 5/25/2017.
 */
@Configuration
@Profile("redis")
@EnableRedisHttpSession
public class SpringSessionConfig {
}
