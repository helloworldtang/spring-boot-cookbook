package com.tangcheng.learning.util;

import org.junit.Test;

import java.util.Date;

/**
 * @Auther: cheng.tang
 * @Date: 2019/4/2
 * @Description:
 */
public class DateUtilTest {

    @Test
    public void convertToDate() {
        Date date = DateUtil.convertToDate("2018-12-17T15:17:22");
        System.out.println(date);
    }



}