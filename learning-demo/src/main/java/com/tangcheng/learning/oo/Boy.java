package com.tangcheng.learning.oo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: cheng.tang
 * @date: 2020/9/3
 * @see
 * @since
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class Boy extends Person {

    private Boolean sex;

    /**
     * 呼吸
     */
    @Override
    public void breathe() {
        log.info("Boy breathe normally");
    }

}
