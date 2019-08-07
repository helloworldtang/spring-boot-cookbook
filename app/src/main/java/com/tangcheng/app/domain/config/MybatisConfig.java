package com.tangcheng.app.domain.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tang.cheng on 2017/4/22.
 */
@Configuration
@MapperScan("com.tangcheng.app.domain.mapper")
public class MybatisConfig {
}
