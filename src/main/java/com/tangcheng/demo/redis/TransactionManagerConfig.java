package com.tangcheng.demo.redis;

import org.springframework.context.annotation.Configuration;

/**
 * Created by MyWorld on 2016/9/16.
 */
@Configuration
public class TransactionManagerConfig/* implements TransactionManagementConfigurer*/ {
    /*
            在Spring Boot中，当我们使用了spring-boot-starter-jdbc或spring-boot-starter-data-jpa依赖的时候，
            框架会自动默认分别注入DataSourceTransactionManager或JpaTransactionManager。
            所以我们不需要任何额外 配置就可以用@Transactional注解进行事务的使用。*/
  /*  @Resource(name = "defaultTM")
    private PlatformTransactionManager defaultTransactionManager;*/

/*       @Bean
        public DataSource dataSource() {
            DruidDataSource ds = new DruidDataSource();
            //todo add customer custom
            return ds;
        }*/


 /*   @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&useCompression=true" +
                "&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setAcquireIncrement(10);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setMaxPoolSize(100);
        dataSource.setMaxStatements(50);
        dataSource.setMinPoolSize(10);

        return dataSource;
    }*/

/*
    @Bean(name = "defaultTM")
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    *//**
     * 其返回值代表在拥有多个事务管理器的情况下默认使用的事务管理器
     *
     * @return
     *//*
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return defaultTransactionManager;
    }*/


}
