package com.tangcheng.learning.reflect;

import com.tangcheng.learning.domain.bo.OneLevelChildClass;
import com.tangcheng.learning.domain.bo.ParentClass;
import com.tangcheng.learning.domain.bo.TwoLevelChildClass;
import org.junit.Test;

import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/08/02 17:54
 */
public class TestClassAPI {

    @Test
    public void test_isPrimitive_Method() {
        assertThat(Integer.class.isPrimitive()).isFalse();
        assertThat(int.class.isPrimitive()).isTrue();
    }


    @Test
    public void test_instanceof() {
        /**
         * instanceof运算符 只被用于对象引用变量，检查左边的被测试对象 是不是 右边类或接口的 实例化。
         * 如果被测对象是null值，则测试结果总是false。
         */
        ParentClass parentClass = new ParentClass();
        assertThat(parentClass instanceof Serializable).isEqualTo(true);
        assertThat(parentClass instanceof ParentClass).isEqualTo(true);
        assertThat(parentClass instanceof OneLevelChildClass).isEqualTo(false);
        assertThat(parentClass instanceof TwoLevelChildClass).isEqualTo(false);

        OneLevelChildClass oneLevelChildClass = new OneLevelChildClass();
        assertThat(oneLevelChildClass instanceof Serializable).isEqualTo(true);
        assertThat(oneLevelChildClass instanceof ParentClass).isEqualTo(true);
        assertThat(oneLevelChildClass instanceof OneLevelChildClass).isEqualTo(true);
        assertThat(oneLevelChildClass instanceof TwoLevelChildClass).isEqualTo(false);

        TwoLevelChildClass twoLevelChildClass = new TwoLevelChildClass();
        assertThat(twoLevelChildClass instanceof Serializable).isEqualTo(true);
        assertThat(twoLevelChildClass instanceof ParentClass).isEqualTo(true);
        assertThat(twoLevelChildClass instanceof OneLevelChildClass).isEqualTo(true);
        assertThat(twoLevelChildClass instanceof TwoLevelChildClass).isEqualTo(true);
    }

    @Test
    public void test_isInstance() {
        /**
         * 作用范围与instanceof相同
         */
        ParentClass parentClass = new ParentClass();
        assertThat(Serializable.class.isInstance(parentClass)).isEqualTo(true);
        assertThat(ParentClass.class.isInstance(parentClass)).isEqualTo(true);
        assertThat(OneLevelChildClass.class.isInstance(parentClass)).isEqualTo(false);
        assertThat(TwoLevelChildClass.class.isInstance(parentClass)).isEqualTo(false);

        OneLevelChildClass oneLevelChildClass = new OneLevelChildClass();
        assertThat(Serializable.class.isInstance(oneLevelChildClass)).isEqualTo(true);
        assertThat(ParentClass.class.isInstance(oneLevelChildClass)).isEqualTo(true);
        assertThat(OneLevelChildClass.class.isInstance(oneLevelChildClass)).isEqualTo(true);
        assertThat(TwoLevelChildClass.class.isInstance(oneLevelChildClass)).isEqualTo(false);

        TwoLevelChildClass twoLevelChildClass = new TwoLevelChildClass();
        assertThat(Serializable.class.isInstance(twoLevelChildClass)).isEqualTo(true);
        assertThat(ParentClass.class.isInstance(twoLevelChildClass)).isEqualTo(true);
        assertThat(OneLevelChildClass.class.isInstance(twoLevelChildClass)).isEqualTo(true);
        assertThat(TwoLevelChildClass.class.isInstance(twoLevelChildClass)).isEqualTo(true);
    }


    @Test
    public void test_isAssignableFrom() {
        ParentClass parentClass = new ParentClass();
        assertThat(parentClass.getClass().isAssignableFrom(Serializable.class)).isEqualTo(false);
        /**
         * 当前类及子类都返回true。父类及接口返回false
         */
        assertThat(parentClass.getClass().isAssignableFrom(ParentClass.class)).isEqualTo(true);
        assertThat(parentClass.getClass().isAssignableFrom(OneLevelChildClass.class)).isEqualTo(true);
        assertThat(parentClass.getClass().isAssignableFrom(TwoLevelChildClass.class)).isEqualTo(true);

        OneLevelChildClass oneLevelChildClass = new OneLevelChildClass();
        assertThat(oneLevelChildClass.getClass().isAssignableFrom(Serializable.class)).isEqualTo(false);
        assertThat(oneLevelChildClass.getClass().isAssignableFrom(ParentClass.class)).isEqualTo(false);
        /**
         * 当前类及子类都返回true。父类及接口返回false
         */
        assertThat(oneLevelChildClass.getClass().isAssignableFrom(OneLevelChildClass.class)).isEqualTo(true);
        assertThat(oneLevelChildClass.getClass().isAssignableFrom(TwoLevelChildClass.class)).isEqualTo(true);

        TwoLevelChildClass twoLevelChildClass = new TwoLevelChildClass();
        assertThat(twoLevelChildClass.getClass().isAssignableFrom(Serializable.class)).isEqualTo(false);
        assertThat(twoLevelChildClass.getClass().isAssignableFrom(ParentClass.class)).isEqualTo(false);
        assertThat(twoLevelChildClass.getClass().isAssignableFrom(OneLevelChildClass.class)).isEqualTo(false);
        /**
         * 当前类及子类都返回true。父类及接口返回false
         */
        assertThat(twoLevelChildClass.getClass().isAssignableFrom(TwoLevelChildClass.class)).isEqualTo(true);
    }


    @Test
    public void test_Array_instanceof_isInstance_isAssignableFrom() {
        Integer[] intTypes = {1, 2};
        assertThat(intTypes.getClass().isArray()).isTrue();
        assertThat(intTypes instanceof Integer[]).isTrue();
        /**
         * 会编译报错：不兼容的类型
         */
//        assertThat(intTypes instanceof int[]);
        assertThat(intTypes instanceof Object[]).isTrue();

        assertThat(Integer[].class.isInstance(intTypes)).isTrue();
        assertThat(Object[].class.isInstance(intTypes)).isTrue();
        /**
         * 会编译报错：不兼容的类型
         */
//        assertThat(intTypes instanceof String[]).isTrue();
        assertThat(intTypes.getClass().isAssignableFrom(Object[].class)).isFalse();
        assertThat(intTypes.getClass().isAssignableFrom(Integer[].class)).isTrue();

        /**
         * 当前类及子类都返回true。父类及接口返回false
         */
        assertThat(intTypes.getClass().isAssignableFrom(Object[].class)).isFalse();
        assertThat(intTypes.getClass().isAssignableFrom(Integer[].class)).isTrue();


        Object[] objects = {1, 2};
        assertThat(objects instanceof Integer[]).isFalse();
        assertThat(objects instanceof Object[]).isTrue();

        assertThat(Integer[].class.isInstance(objects)).isFalse();//具体到抽象的为false
        assertThat(Object[].class.isInstance(objects)).isTrue();

        /**
         * 当前类及子类都返回true。父类及接口返回false
         */
        assertThat(objects.getClass().isAssignableFrom(Object[].class)).isTrue();
        assertThat(objects.getClass().isAssignableFrom(Integer[].class)).isTrue();
    }


}
