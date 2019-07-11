package com.tangcheng.learning.awt;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class RandomUtils {

    /**
     * 根据UUID生成一个唯一标识
     *
     * @return
     */
    public static String generateTicket() {
        String ticket = UUID.randomUUID().toString();
        return ticket.replaceAll("-", "");
    }

    /**
     * 获取一个随机的字符串
     *
     * @param count
     * @return
     */
    public static String generateRandomString(int count) {
        return RandomStringUtils.random(count, true, true);
    }

    public static String generateRandomNum(int count) {
        return RandomStringUtils.random(count, false, true);
    }


    public static String generateRandomFileName() {
        return String.join("", generateTicket(), generateRandomString(6));
    }


}
