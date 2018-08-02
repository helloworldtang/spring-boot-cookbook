package com.tangcheng.learning.reflect;

import org.junit.Test;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/08/02 17:54
 */
public class TestObjectIsPrivate {

    @Test
    public void testPrivateMethod() {
        boolean primitive = Integer.class.isPrimitive();
        System.out.println(primitive);
    }


}
