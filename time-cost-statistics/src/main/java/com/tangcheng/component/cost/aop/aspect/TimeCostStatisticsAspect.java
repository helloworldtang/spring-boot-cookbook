package com.tangcheng.component.cost.aop.aspect;

import com.tangcheng.component.cost.aop.annotation.TimeCostStatistics;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * spring-boot-cookbook
 *
 * @Auther: cheng.tang
 * @Date: 2022/7/26 10:52 PM
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class TimeCostStatisticsAspect {

    @Pointcut("@annotation(timeCostStatistics)")
    public void timeCostStatisticsPointcut(TimeCostStatistics timeCostStatistics) {
    }

    @Around(value = "timeCostStatisticsPointcut(timeCostStatistics)", argNames = "proceedingJoinPoint,timeCostStatistics")
    public Object timeCostStatisticsHandle(ProceedingJoinPoint proceedingJoinPoint, TimeCostStatistics timeCostStatistics) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Class<?> classTarget = proceedingJoinPoint.getTarget().getClass();
        Class<?>[] parameterTypes = methodSignature.getParameterTypes();
        Method method = classTarget.getMethod(methodSignature.getName(), parameterTypes);
        String bizFlag = timeCostStatistics.bizFlag();
        String key = timeCostStatistics.key();
        key = this.parseSpringEL(key, method, proceedingJoinPoint.getArgs());

        log.info("TimeCostStatistics bizFlag : {}  key : {} begin", bizFlag, key);
        long startMs = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            TimeUnit logTimeUnit = timeCostStatistics.timeUnit();
            long costTime = logTimeUnit.convert(System.currentTimeMillis() - startMs, TimeUnit.MILLISECONDS);
            log.info("TimeCostStatistics bizFlag : {} key : {}  end cost : {} {}", bizFlag, key, costTime, logTimeUnit.name());
        }
    }

    private String parseSpringEL(String key, Method method, Object[] args) {
        if (StringUtils.isEmpty(key)) {
            if (log.isDebugEnabled()) {
                log.debug("没有指定key");
            }
            return "";
        }
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        if (parameterNames == null || parameterNames.length == 0 || args == null || args.length == 0) {
            if (log.isDebugEnabled()) {
                log.debug("没有可以用来渲染Key的参数 {} ", key);
            }
            return key;
        }
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; ++i) {
            context.setVariable(parameterNames[i], args[i]);
        }
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        return spelExpressionParser.parseExpression(key).getValue(context, String.class);
    }

}
