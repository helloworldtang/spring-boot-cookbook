package com.tangcheng.learning.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultThreadFactory implements ThreadFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultThreadFactory.class);

    final private static String DEFAULT_POOL = "default-pool";
    final private String name;
    final private AtomicInteger threadNumber = new AtomicInteger(0);

    public DefaultThreadFactory() {
        this(DEFAULT_POOL);
    }

    public DefaultThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, name);
        String threadName = String.format(
                "Task[%s-%d]", name,
                threadNumber.incrementAndGet());
        thread.setName(threadName);
        LOGGER.info("{}", threadName);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }

        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        thread.setUncaughtExceptionHandler((t, e) -> LOGGER.error("{},mgs:{}", t, e.getMessage(), e));
        return thread;
    }
}