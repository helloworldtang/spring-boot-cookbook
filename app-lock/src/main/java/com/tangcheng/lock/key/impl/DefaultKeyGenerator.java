package com.tangcheng.lock.key.impl;

import com.tangcheng.lock.annotation.DistributedLock;
import com.tangcheng.lock.annotation.KeyParam;
import com.tangcheng.lock.key.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * spring-boot-cookbook
 * 分布式锁的Key的默认生成器
 *
 * @author tangcheng
 * @date 6/16/2018 11:52 PM
 */
@Slf4j
@Component
public class DefaultKeyGenerator implements KeyGenerator {

    /**
     * 如果对象中嵌套对象，则取不到@KeyParam的字段
     *
     * @param proceedingJoinPoint
     * @param distributedLock
     * @return
     */
    @Override
    public String getLockKey(ProceedingJoinPoint proceedingJoinPoint, DistributedLock distributedLock) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = proceedingJoinPoint.getArgs();
        Map<String, String> paramsKey = new HashMap<>(32);
        String delimiter = distributedLock.delimiter();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter == null) {
                continue;
            }
            KeyParam keyParamAnnotation = parameter.getAnnotation(KeyParam.class);
            if (keyParamAnnotation == null) {
                //如果入参没有加@KeyParam，则看对象里面的字段有没有加@KeyParam
                Object fieldObject = args[i];
                if (fieldObject == null) {
                    continue;
                }
                Class<?> parameterClass = fieldObject.getClass();
                findKeyParamAndAddFillRedisKey(paramsKey, delimiter, fieldObject, parameterClass);
                //查找父类的字段
                continue;
            }
            addRedisKey(paramsKey, parameter.getName(), keyParamAnnotation.value(), delimiter, args[i]);
        }
        String redisKeyByBizParams = sortByParamNamesAndJoinByDelimiter(paramsKey, delimiter);
        return distributedLock.prefix() + redisKeyByBizParams;
    }


    private String sortByParamNamesAndJoinByDelimiter(Map<String, String> paramsKey, String delimiter) {
        if (CollectionUtils.isEmpty(paramsKey)) {
            log.warn("没有配置业务参数【不规范的操作】");
            return "";
        }
        List<Map.Entry<String, String>> list = new ArrayList<>(paramsKey.entrySet());
        //升序排序
        list.sort(Comparator.comparing(Map.Entry::getKey));
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : list) {
            result.append(delimiter)
                    .append(entry.getKey())
                    .append(delimiter)
                    .append(entry.getValue());
        }
        return result.toString();
    }


    /**
     * 查找Req中有没有使用@KeyParam
     *
     * @param paramsKey
     * @param delimiter
     * @param fieldObject
     * @param parameterClass
     */
    private void findKeyParamAndAddFillRedisKey(Map<String, String> paramsKey, String delimiter, Object fieldObject, Class<?> parameterClass) {
        if (parameterClass.isPrimitive()
                || parameterClass.isAssignableFrom(String.class)
                || parameterClass.isAssignableFrom(Integer.class)
                || parameterClass.isAssignableFrom(Long.class) || parameterClass.isAssignableFrom(Byte.class)
                || parameterClass.isAssignableFrom(Boolean.class) || parameterClass.isAssignableFrom(Short.class)
                || parameterClass.isAssignableFrom(Float.class) || parameterClass.isAssignableFrom(Double.class)
                || parameterClass.isAssignableFrom(Character.class)
                || parameterClass.isArray()) {
            return;
        }
        ReflectionUtils.doWithFields(parameterClass, field -> {
            KeyParam annotation = field.getAnnotation(KeyParam.class);
            field.setAccessible(true);
            Object fieldValue = ReflectionUtils.getField(field, fieldObject);
            addRedisKey(paramsKey, field.getName(), annotation.value(), delimiter, fieldValue);
        }, field -> {
            KeyParam annotation = field.getAnnotation(KeyParam.class);
            if (Objects.isNull(annotation)) {
                return false;
            }
            return true;
        });
    }

    /**
     * 将注解中key-value拼装到redis-key
     *
     * @param paramsKey
     * @param fieldName
     * @param annotationValue
     * @param delimiter
     * @param fileValue
     */
    private void addRedisKey(Map<String, String> paramsKey, String fieldName, String annotationValue, String delimiter, Object fileValue) {
        if (Objects.isNull(fileValue) || StringUtils.isBlank(fileValue.toString())) {
            return;
        }
        if (StringUtils.isBlank(annotationValue)) {
            annotationValue = fieldName;
        }
        paramsKey.put(annotationValue, fileValue.toString());
    }


}
