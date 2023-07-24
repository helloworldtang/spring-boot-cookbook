package com.tangcheng.component.log.aop.aspect;

import com.tangcheng.component.log.aop.annotation.LogReqBizParameters;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Auther: cheng.tang
 * @Date: 2023/7/24
 * @Description: spring-boot-cookbook
 */
@Slf4j
@Aspect
@Component
public class LogReqBizParametersAspect {


    @Value(("${custom.header.name:THE_BIZ_FLAG}"))
    private String customHeaderName;

    /**
     * 定义一个切入点：
     * 满足下面条件的，会走到特定逻辑：
     */
    @Around("@annotation(logReqBizParameters)")
    public Object logParameter(ProceedingJoinPoint joinPoint, LogReqBizParameters logReqBizParameters) throws Throwable {
        StringBuilder logResult = new StringBuilder(logReqBizParameters.bizKey());
        logResult.append(" ");
        logResult.append(logReqBizParameters.value());
        logResult.append(" ");
        appendMethodParams(joinPoint, logResult);
        appendRequestParams(logResult);
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            logResult.append(" cost:").append(System.currentTimeMillis() - start).append("ms");
            log.info("{}", logResult);
        }
    }

    private void appendMethodParams(ProceedingJoinPoint joinPoint, StringBuilder logResult) {
        /**
         * 获取参数值
         */
        Object[] args = joinPoint.getArgs();
        /**
         * 获取到方法签名
         */
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logResult.append(methodSignature.getDeclaringType().getName()).append("#").append(methodSignature.getName());
        /**
         * 获取方法参数名称
         */
        String[] parameterNames = methodSignature.getParameterNames();
        if (Objects.nonNull(parameterNames) && Objects.nonNull(args)
                && parameterNames.length > 0 && args.length > 0
                && parameterNames.length == args.length) {
            for (int i = 0; i < parameterNames.length; i++) {
                Object arg = args[i];
                if (Objects.nonNull(arg) &&
                        (arg instanceof ServletRequest ||
                                arg instanceof ServletResponse ||
                                arg instanceof HttpSession ||
                                arg instanceof Errors ||
                                arg instanceof WebRequest ||
                                arg instanceof MultipartFile
                        )) {
                    continue;
                }
                logResult.append(",").append(parameterNames[i]).append(":").append(arg);
            }
        }
    }

    private void appendRequestParams(StringBuilder logResult) {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (Objects.nonNull(requestAttributes)) {
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
                String theBizFlagVal = request.getHeader(customHeaderName);
                logResult.append(", Header ").append(customHeaderName).append(":").append(theBizFlagVal);
                logResult.append(", url : ").append(request.getRequestURL());
            }
        } catch (Exception ignored) {
        }
    }


}
