package com.service;

import com.api.LongTermTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class LongTimeAsyncCallService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LongTimeAsyncCallService.class);

    private final int CorePoolSize = 4;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CorePoolSize);

    public void makeRemoteCallAndUnknownWhenFinish(LongTermTask task) {
        int needSeconds = 3;
        LOGGER.info("完成此任务需要:{}秒,{}", needSeconds, Thread.currentThread());
        scheduler.schedule(() -> task.callback("长时间异步调用完成."), needSeconds, TimeUnit.SECONDS);
    }
}