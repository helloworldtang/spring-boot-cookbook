package com.tangcheng.config.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfiguration {

    public static final Logger LOGGER = LoggerFactory.getLogger(DruidConfiguration.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            /**
             *
             属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
             监控统计用的filter:stat
             日志用的filter:log4j
             防御sql注入的filter:wall
             如果启用wall，执行建表语句会报错：
             Caused by: java.sql.SQLException: sql injection violation, comment not allow : CREATE TABLE `test`.`schema_version` (
             。。。。。。
             at com.alibaba.druid.wall.WallFilter.check(WallFilter.java:726)
             https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8
             */
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return druidDataSource;
    }
}