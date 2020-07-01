package com.tangcheng.learning.reflect;

import com.tangcheng.learning.listener.EventHandler;
import com.tangcheng.learning.listener.IntegerEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    @Test
    public void checkMethodSignature() {
        Integer integerSignature = 1;
        EventHandler<Integer> eventHandler = new IntegerEventHandler();
        Class<?> searchType = eventHandler.getClass();
        while (searchType != null) {
            Method[] methods = (searchType.isInterface() ? searchType.getMethods() : getDeclareMethods(searchType));
            for (Method method : methods) {
                if ("support".equals(method.getName())) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    for (Class<?> parameterType : parameterTypes) {
                        if (Objects.equals(parameterType, integerSignature.getClass())) {
                            System.out.println(searchType.getName() + ":" + method.getName() + ":ParameterTypes.length:" + parameterTypes.length + ":success");
                        }
                    }
                }
            }
            searchType = searchType.getSuperclass();
            if (Objects.equals(searchType, Object.class)) {
                break;
            }
        }
    }


    private Method[] getDeclareMethods(Class<?> clazz) {
        Method[] result;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<Method> defaultMethods = findConcreteMethodsOnInterfaces(clazz);
        if (defaultMethods != null) {
            result = new Method[declaredMethods.length + defaultMethods.size()];
            System.arraycopy(declaredMethods, 0, result, 0, declaredMethods.length);
            int index = declaredMethods.length;
            for (Method defaultMethod : defaultMethods) {
                result[index] = defaultMethod;
                index++;
            }
        } else {
            result = declaredMethods;
        }
        return result;
    }

    private List<Method> findConcreteMethodsOnInterfaces(Class<?> clazz) {
        List<Method> result = null;
        for (Class<?> ifc : clazz.getInterfaces()) {
            for (Method ifcMethod : ifc.getMethods()) {
                if (!Modifier.isAbstract(ifcMethod.getModifiers())) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(ifcMethod);
                }
            }
        }
        return result;
    }


}
