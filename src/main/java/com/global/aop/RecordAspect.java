package com.global.aop;

import com.api.MethodLogAnnotation;
import com.util.NetworkUtil;
import com.util.RequestHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by tang.cheng on 2016/12/2.
 */
@Component
@Aspect
public class RecordAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordAspect.class);

    @Pointcut("@annotation(com.api.MethodLogAnnotation)")
    public void methodLog() {
    }

    /**
     * Caused by: java.lang.IllegalArgumentException: ProceedingJoinPoint is only supported for around advice
     * at org.springframework.aop.aspectj.AbstractAspectJAdvice.maybeBindProceedingJoinPoint(AbstractAspectJAdvice.java:405)
     *
     * @param joinPoint
     * @throws ClassNotFoundException
     */
    @Around("methodLog()")
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest httpServletRequest = RequestHolder.getRequestFacade();
        String remoteIp = NetworkUtil.getRemoteIp(httpServletRequest);
        LOGGER.info("{},{},{},{}", remoteIp, httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), httpServletRequest.getRequestURL());
        

        Method invokingMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        MethodLogAnnotation annotation = invokingMethod.getAnnotation(MethodLogAnnotation.class);
        LOGGER.info("business name:{}", annotation.desc());
        return joinPoint.proceed();
    }


}
