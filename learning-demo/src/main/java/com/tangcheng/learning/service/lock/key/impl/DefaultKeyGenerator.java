package com.tangcheng.learning.service.lock.key.impl;

import com.tangcheng.learning.service.lock.annotation.DistributedLock;
import com.tangcheng.learning.service.lock.annotation.KeyParam;
import com.tangcheng.learning.service.lock.key.KeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * spring-boot-cookbook
 * 分布式锁的Key的默认生成器
 *
 * @author tangcheng
 * @date 6/16/2018 11:52 PM
 */
@Component
public class DefaultKeyGenerator implements KeyGenerator {

    @Override
    public String getLockKey(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        Parameter[] parameters = method.getParameters();
        Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder builder = new StringBuilder();
        String delimiter = distributedLock.delimiter();
        for (int i = 0; i < parameters.length; i++) {
            KeyParam annotation = parameters[i].getAnnotation(KeyParam.class);
            if (annotation == null) {
                continue;
            }
            builder.append(delimiter)
                    .append(annotation.name())
                    .append(delimiter)
                    .append(args[i]);
        }
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Object object = args[i];
            Field[] declaredFields = object.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                KeyParam annotation = field.getAnnotation(KeyParam.class);
                if (annotation == null) {
                    continue;
                }
                field.setAccessible(true);
                builder.append(delimiter)
                        .append(annotation.name())
                        .append(delimiter)
                        .append(ReflectionUtils.getField(field, object));
            }
        }
        return distributedLock.prefix() + builder.toString();
    }


}
