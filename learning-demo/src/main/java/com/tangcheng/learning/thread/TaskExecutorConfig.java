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
import java.util.concurrent.ThreadPoolExecutor;

/**
 * https://mp.weixin.qq.com/s/EkyxDK1Q27TdEZy9B0t-yA
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
        taskExecutor.setQueueCapacity(25);//如果任务数大于corePoolSize则放到Queue中，queue只能存放25个阻塞的任务
        //如果需要执行的任务数大于corePoolSize+QueueCapacity则会创建(maxPoolSize-corePoolSize)个线程来继续执行任务
        taskExecutor.setMaxPoolSize(20);
        //如果大于需要执行的任务数大于maxPoolSize+QueueCapacity,
        // 则会根据RejectedExecutionHandler策略来决定怎么处理这些超出上面配置的任务
        //此处使用CallerRunsPolicy：会通过new 一个Thread的方式来执行这些任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setKeepAliveSeconds(60);//实际工作的线程数大于corePoolSize时，如果60s没有被使用则销毁

        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);//线程池会等任务完成后才会关闭
        taskExecutor.setAwaitTerminationSeconds(60);//如果60s后线程仍未关闭，则关闭线程池
        taskExecutor.setThreadNamePrefix("ThreadPoolTaskExecutor-");//实际显示为： [lTaskExecutor-1] c.t.learning.thread.AsyncTaskService     :
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
