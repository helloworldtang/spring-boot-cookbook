package com.tangcheng.learning.oo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: cheng.tang
 * @date: 2020/9/3
 * @see
 * @since
 */
@Slf4j
@Data
public class Person {

    private Long id;

    /**
     * 呼吸
     */
    public void breathe() {
        log.info("Person breathe normally");
    }

}
