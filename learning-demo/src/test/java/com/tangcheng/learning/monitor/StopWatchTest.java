package com.tangcheng.learning.monitor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/06/26 17:24
 */
@Slf4j
public class StopWatchTest {

    @Test
    public void usageTest() throws InterruptedException {
        StopWatch sw = new StopWatch();

        sw.start("起床");
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        sw.stop();

        sw.start("洗漱");
        Thread.sleep(ThreadLocalRandom.current().nextInt(100));
        sw.stop();

        sw.start("吃饭");
        Thread.sleep(ThreadLocalRandom.current().nextInt(100));
        sw.stop();

        log.info("{}", sw.prettyPrint());
        log.info("{}", sw.getTotalTimeMillis());
        log.info("{}", sw.getLastTaskName());
        log.info("{}", JSON.toJSONString(sw.getLastTaskInfo()));
        log.info("{}", sw.getTaskCount());
    }


}
