package com.tangcheng.learning.oo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: cheng.tang
 * @date: 2020/9/3
 * @see
 * @since
 */
public class BoyTest {

    /**
     * 19:54:19.477 [main] INFO com.tangcheng.learning.oo.Boy - Boy breathe normally
     */
    @Test
    public void breathe() {
        Person person = new Boy();
        person.breathe();
    }


}
