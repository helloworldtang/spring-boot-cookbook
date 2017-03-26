package com.tangcheng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by MyWorld on 2016/9/16.
 */
//@Configuration
@EnableTransactionManagement// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
public class TxConfig {

//    @Autowired
//    private DataSource dataSource;

    /**
     * 在Spring Boot中，当我们使用了spring-boot-starter-jdbc或spring-boot-starter-data-jpa依赖的时候，
     * 框架会自动默认分别注入DataSourceTransactionManager或JpaTransactionManager。
     * 所以我们不需要任何额外 配置就可以用@Transactional注解进行事务的使用。
     *
     * @return
     * @throws SQLException
     */
//    @Bean
//    public PlatformTransactionManager transactionManager() throws SQLException {
//        return new DataSourceTransactionManager(dataSource);
//    }

}
