package com.tangcheng.learning.reflect;

import com.tangcheng.learning.listener.EventHandler;
import com.tangcheng.learning.listener.IntegerEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: cheng.tang
 * @date: 2020/6/30
 * @see
 * @since
 */
@Slf4j
public class GenericInterfaceTest {

    @Test
    public void testGenericInterface() {
        EventHandler<Integer> eventHandler = new IntegerEventHandler();
        Method supportMethod = ReflectionUtils.findMethod(eventHandler.getClass(), "support", Integer.class);
        assertThat(supportMethod).isNotNull();

        Method longSupportMethod = ReflectionUtils.findMethod(eventHandler.getClass(), "support", String.class);
        assertThat(longSupportMethod).isNull();

        Method notExistsMethod = ReflectionUtils.findMethod(eventHandler.getClass(), "notExists", Integer.class);
        assertThat(notExistsMethod).isNull();

    }


    @Test
    public void testGenericInterfaceWithTwoParameters() {
        EventHandler<Integer> eventHandler = new IntegerEventHandler();
        Method stringIntegerSupportMethod = ReflectionUtils.findMethod(eventHandler.getClass(), "support", String.class, Integer.class);
        assertThat(stringIntegerSupportMethod).isNotNull();

        Method methodParameterCanntChangeOrder = ReflectionUtils.findMethod(eventHandler.getClass(), "support", Integer.class, String.class);
        assertThat(methodParameterCanntChangeOrder).isNull();
    }


}
