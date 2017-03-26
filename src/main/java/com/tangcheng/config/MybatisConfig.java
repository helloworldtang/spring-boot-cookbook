package com.tangcheng.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tangcheng on 3/26/2017.
 */
@Configuration
@MapperScan(basePackages = "com.tangcheng.db.mapper")
public class MybatisConfig {
}
