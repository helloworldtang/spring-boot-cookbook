package com.tangcheng.app.service.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-27  15:54
 */
@Service
public class UploadServiceImpl implements UploadService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void countUploadFiles() {
        String key = "UPLOADED_FILE_NUM";
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long num = redisAtomicLong.incrementAndGet();
        LOGGER.info("uploaded {} files", num);
    }

}
