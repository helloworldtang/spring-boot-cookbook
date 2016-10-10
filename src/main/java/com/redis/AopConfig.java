package com.redis;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * Created by tang.cheng on 2016/9/14.
 */
@EnableTransactionManagement // 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
//@AutoConfigureAfter(DataSourceConfig.class)
public class AopConfig {
/*
    @Bean
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED");
        attributes.setProperty("put*", "PROPAGATION_REQUIRED");
        attributes.setProperty("post*", "PROPAGATION_REQUIRED");
        attributes.setProperty("delete*", "PROPAGATION_REQUIRED");
        attributes.setProperty("do*", "PROPAGATION_REQUIRED");
        return new TransactionInterceptor(platformTransactionManager, attributes);
    }

    @Bean
    public AspectJExpressionPointcut aspectJExpressionPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.redis.RedisDemo.*(..)) ");
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(TransactionInterceptor advice,AspectJExpressionPointcut pointcut) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(advice);
        return advisor;
    }*/

/**
 * <tx:advice id="txAdvice" transaction-manager="txManager">//只是定义了AOP通知，用于把事务边界通知给方法
 *     <tx:attributes>
 *         <tx:method name="save*" propagation="REQUIRED"></tx:method>
 *         <tx:method name="*" propagation="SUPPORTs" read-only="true"></tx:method>
 *     </tx:attributes>
 *   </tx:advice>
 *
 *   <aop:config>
 *       <aop:advisor pointcut="execute(* *..SpitterService.*(..)"  advice-ref="txAdvice">
 *       </aop:advisor>
 *   </aop:config>
 */
}
