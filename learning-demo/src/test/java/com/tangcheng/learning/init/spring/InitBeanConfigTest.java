package com.tangcheng.learning.init.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/6/2019 11:02 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InitBeanConfig.class)
public class InitBeanConfigTest {

    @Test
    public void givenBean_WhenInit_thenFirstConstructorFunction_SecondPostConstructAnna_ThirdInitMethod() {
        /**
         * step1:执行构造函数
         * step2:执行使用@PostConstruct注解修饰的方法【如果存在多个使用注解@PostConstruct修饰的方法，则这几个方法的执行顺序不确定】
         * step3:执行@Bean注解中initMethod属性指定的方法
         */
    }


}