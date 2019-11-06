package com.tangcheng.learning.reflect;

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

    /**
     * 看看获取java.lang包下类中类的情况
     */
    @Test
    public void springReflectionUtilsOriginClass() {
        final List<String> list = new ArrayList<String>();
        ReflectionUtils.doWithFields(String.class, field -> list.add(field.getName()));
        System.out.println(list); //[value, hash, serialVersionUID, serialPersistentFields, CASE_INSENSITIVE_ORDER]
        list.clear();
        ReflectionUtils.doWithFields(Integer.class, field -> list.add(field.getName()));
        System.out.println(list); //[MIN_VALUE, MAX_VALUE, TYPE, digits, DigitTens, DigitOnes, sizeTable, value, SIZE, BYTES, serialVersionUID, serialVersionUID]
    }

    @Test
    public void springReflectionUtilsTest1() {
        final List<String> list = new ArrayList<String>();
        TwoLevelChildClass twoLevelChildClass = new TwoLevelChildClass();
        twoLevelChildClass.setTwoLevelChildName("TwoLevelChildName");
        twoLevelChildClass.setOneLevelChildName("OneLevelChildName");
        twoLevelChildClass.setName("Name");
        ReflectionUtils.doWithFields(TwoLevelChildClass.class, new ReflectionUtils.FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException,
                    IllegalAccessException {
                list.add(field.getName());
                field.setAccessible(true);//Class org.springframework.util.ReflectionUtils can not access a member of class com.tangcheng.learning.reflect.StringEqualsTest$TwoLevelChildClass with modifiers "private"
                Object o = ReflectionUtils.getField(field, twoLevelChildClass);
                System.out.println(o); // TwoLevelChildName  \n OneLevelChildName  \n  Name
            }
        });
        System.out.println(list); //[twoLevelChildName, oneLevelChildName, name]
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
        private String twoLevelChildName;
    }


}
