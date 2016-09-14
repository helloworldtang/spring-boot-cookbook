package com.redis;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by tang.cheng on 2016/9/14.
 */
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
public class AopConfig implements TransactionManagementConfigurer {

    @Autowired
    DataSource dataSource;

    @Bean
    @Override
    @ConditionalOnMissingBean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionInterceptor transactionInterceptor() {
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED");
        attributes.setProperty("put*", "PROPAGATION_REQUIRED");
        attributes.setProperty("post*", "PROPAGATION_REQUIRED");
        attributes.setProperty("delete*", "PROPAGATION_REQUIRED");
        attributes.setProperty("do*", "PROPAGATION_REQUIRED");
        TransactionInterceptor txAdvice = new TransactionInterceptor(annotationDrivenTransactionManager(), attributes);
        return txAdvice;
    }

    @Bean
    public AspectJExpressionPointcut aspectJExpressionPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        String transactionExecution = "execution(* com.redis.RedisDemo.doBiz(..)) ";
        pointcut.setExpression(transactionExecution);
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(aspectJExpressionPointcut());
        advisor.setAdvice(transactionInterceptor());
        return advisor;
    }

}
