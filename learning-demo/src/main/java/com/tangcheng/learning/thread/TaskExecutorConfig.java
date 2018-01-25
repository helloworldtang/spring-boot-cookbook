package com.tangcheng.learning.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * Created by MyWorld on 2016/8/11.
 */
@Configuration
@ComponentScan("com.tangcheng")
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskExecutorConfig.class);

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                LOGGER.info("Exception message - {}", ex.getMessage());
                LOGGER.info("Method name - {}", method.getName());
                for (Object param : params) {
                    LOGGER.info("Parameter value - {}", param);
                }
            }
        };
    }
}
