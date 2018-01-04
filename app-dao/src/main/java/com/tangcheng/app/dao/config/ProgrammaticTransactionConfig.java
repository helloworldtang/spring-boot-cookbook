package com.tangcheng.app.dao.config;

import com.google.common.collect.Lists;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 * http://www.spring4all.com/article/286
 *
 * @author tangcheng
 * 2018/01/04
 */
@Aspect
//@Configuration
public class ProgrammaticTransactionConfig implements TransactionManagementConfigurer {
    /**
     * 需要事务管理的包路径
     */
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.tangcheng.app.service.biz.*Impl.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private DataSource dataSource;

    /*事务拦截类型*/
    public TransactionInterceptor defaultTransactionInterceptor(PlatformTransactionManager transactionManager,
                                                                List<Class<? extends Exception>> additionalRollbackRuleExceptions) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        Properties transactionAttributes = new Properties();

        List<RollbackRuleAttribute> rollbackRules = Lists.newArrayList();
        rollbackRules.add(new RollbackRuleAttribute(Exception.class));
        //回滚异常
        if (additionalRollbackRuleExceptions != null && !additionalRollbackRuleExceptions.isEmpty()) {
            for (Class<? extends Exception> clazz : additionalRollbackRuleExceptions) {
                rollbackRules.add(new RollbackRuleAttribute(clazz));
            }
        }
        DefaultTransactionAttribute readOnlyTransactionAttributes =
                new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
        readOnlyTransactionAttributes.setReadOnly(true);

        RuleBasedTransactionAttribute writeTransactionAttributes =
                new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);

        String readOnlyTransactionAttributesDefinition = readOnlyTransactionAttributes.toString();
        String writeTransactionAttributesDefinition = writeTransactionAttributes.toString();
        // read-only
        transactionAttributes.setProperty("is*", readOnlyTransactionAttributesDefinition);
        transactionAttributes.setProperty("has*", readOnlyTransactionAttributesDefinition);
        transactionAttributes.setProperty("get*", readOnlyTransactionAttributesDefinition);
        transactionAttributes.setProperty("list*", readOnlyTransactionAttributesDefinition);
        transactionAttributes.setProperty("search*", readOnlyTransactionAttributesDefinition);
        transactionAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);
        transactionAttributes.setProperty("count*", readOnlyTransactionAttributesDefinition);
        // write et rollback-rule
        transactionAttributes.setProperty("*", writeTransactionAttributesDefinition);

        transactionInterceptor.setTransactionAttributes(transactionAttributes);
        transactionInterceptor.setTransactionManager(this.transactionManager);
        return transactionInterceptor;
    }

    /**
     * 切面拦截规则
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, this.defaultTransactionInterceptor(this.transactionManager, null));
    }

    @Bean(name = "transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
