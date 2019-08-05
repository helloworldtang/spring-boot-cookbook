package com.tangcheng.learning.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by tangcheng on 8/6/2017.
 */
public class VisitCounter {

    public static final Logger LOGGER = LoggerFactory.getLogger(VisitCounter.class);

    private static final Map<String, Long> visitCount = new ConcurrentHashMap<>();

    /**
     * ConcurrentHashMap的get,put是线程安全的。
     * 但增加访问数的操作是由两个步骤完成的：
     * get
     * put
     * 两个线程安全的组合操作，就不是线程安全的
     *
     * @param uri 访问的URI
     */
    public void increment(String uri) throws InterruptedException {
        Long oldCount, newCount;
        int waitTime = 1;
        while (true) {
            oldCount = visitCount.get(uri);
            LOGGER.info("oldCount:{}", oldCount);
            if (oldCount == null) {
                if (visitCount.putIfAbsent(uri, 1L) == null) {
                    LOGGER.info("finish init");
                    break;
                }
            } else {
                newCount = oldCount + 1;
                if (visitCount.replace(uri, oldCount, newCount)) {
                    LOGGER.info("add one :{}", newCount);
                    break;
                }
                LOGGER.info("newCount:{},waitTime:{}", newCount, waitTime);
                TimeUnit.MICROSECONDS.sleep(waitTime++);
            }

        }

    }

    public Long get(String uri) {
        return visitCount.get(uri);
    }

}
