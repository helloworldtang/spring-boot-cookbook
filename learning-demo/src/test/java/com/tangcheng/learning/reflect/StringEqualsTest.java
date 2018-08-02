package com.tangcheng.learning.reflect;

import com.tangcheng.learning.service.lock.annotation.KeyParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/08/02 16:29
 */
public class StringEqualsTest {

    @Test
    public void testEquals() throws IllegalAccessException, NoSuchFieldException {
        String a = "abc";
        Field valueFieldString = String.class.getDeclaredField("value");
        valueFieldString.setAccessible(true);
        char[] value = (char[]) valueFieldString.get(a);
        value[2] = '@';
        String b = "abc";
        //a.intern();
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b); //a和b在内存中的地址是相同的,返回true
        System.out.println("abc" == b);//abc和b在内存中的地址是相同的，返回true
        System.out.println("ab@" == a); //ab@在内存中的地址和a不相同，返回false
        System.out.println(a.equals("ab@"));//"abc"和"ab@"的内存地址不同，但存储的值却是一样的，所以返回 true
        System.out.println(a.equals("abc"));//abc的值和a对应的是同一个内内存地址，所以返回true
        System.out.println("abc".equals("ab@"));//比较的是对象中的值。abc对应String对象的值已经被更改为ab@，所以返回true
    }

    @Test
    public void springReflectionUtilsTest1() {
        final List<String> list = new ArrayList<String>();
        ReflectionUtils.doWithFields(TwoLevelChildClass.class, new ReflectionUtils.FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException,
                    IllegalAccessException {
                list.add(field.getName());
            }
        });
        System.out.println(list);
    }

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
        System.out.println(list);
    }

    @Data
    public static class ParentClass {
        private String name;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class OneLevelChildClass extends ParentClass {
        private String oneLevelChildNmae;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class TwoLevelChildClass extends OneLevelChildClass {
        @KeyParam
        private String twoLevelChildNmae;
    }


}
