package com.tangcheng.learning.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Auther: cheng.tang
 * @Date: 2019/4/1
 * @Description:
 */
@Slf4j
public class DateUtil {

    public static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 转换成Date
     *
     * @param yyyyMMddHHmmssStr
     * @return
     */
    public static Date convertToDate(String yyyyMMddHHmmssStr) {
        if (StringUtils.isBlank(yyyyMMddHHmmssStr)) {
            return null;
        }
        yyyyMMddHHmmssStr = yyyyMMddHHmmssStr.replace("T", " ");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(yyyyMMddHHmmssStr, yyyyMMddHHmmss);
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = localDateTime.atZone(zone).toInstant();
            return Date.from(instant);
        } catch (Exception e) {
            log.error("convertToDate yyyyMMddHHmmssStr:{} msg:{}", yyyyMMddHHmmssStr, e.getMessage(), e);
            return null;
        }
    }

}
