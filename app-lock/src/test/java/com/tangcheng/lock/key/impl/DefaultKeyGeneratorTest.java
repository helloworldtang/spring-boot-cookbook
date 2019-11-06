package com.tangcheng.lock.key.impl;

import com.tangcheng.lock.annotation.KeyParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: cheng.tang
 * @date: 2019/11/6
 * @see
 * @since
 */
public class DefaultKeyGeneratorTest {

    @Test
    public void springReflectionUtilsTest2() {
        final List<String> list = new ArrayList<String>();
        ReflectionUtils.doWithFields(TwoLevelChildClass.class, field -> list.add(field.getName()), field -> {
            KeyParam annotation = field.getAnnotation(KeyParam.class);
            if (annotation != null) {
                return true;
            }
            return false;
        });
        System.out.println(list); //[twoLevelChildName]
    }

    @Data
    public static class ParentClass {
        private String name;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class OneLevelChildClass extends ParentClass {
        private String oneLevelChildName;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class TwoLevelChildClass extends OneLevelChildClass {
        @KeyParam
        private String twoLevelChildName;
    }


}