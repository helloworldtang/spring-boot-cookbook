package com.tangcheng.learning.refect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: cheng.tang
 * @date: 2019/11/4
 * @see
 * @since
 */
@Slf4j
public class ClassIsPrimitiveTest {


    @Test
    public void isPrimitive() {
        log.info("Integer.class.isPrimitive() : {}", Integer.class.isPrimitive());
        log.info("Integer.class.isAssignableFrom(Integer.class) : {}", Integer.class.isAssignableFrom(Integer.class));

        log.info("int.class.isPrimitive() : {}", int.class.isPrimitive());
        log.info("byte.class.isPrimitive() : {}", byte.class.isPrimitive());
        log.info("char.class.isPrimitive() : {}", char.class.isPrimitive());
        log.info("void.class.isPrimitive() : {}", void.class.isPrimitive());
        log.info("Integer.class.isAssignableFrom(Integer.class) : {}", int.class.isAssignableFrom(Integer.class));
    }


}
