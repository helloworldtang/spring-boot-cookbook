package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by tang.cheng on 2016/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class Main {

    //DEBUG [main][org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor]
    // Autowiring by type from bean name 'com.test.Main' to bean named 'testA'
    @Autowired
    private ITest testA;//一个class中注入多个同一类型的Bean，会将变量名作为注册到ApplicationContext的Bean名来进行匹配


    //DEBUG [main][org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor]
    // Autowiring by type from bean name 'com.test.Main' to bean named 'test2'
    @Autowired
//    @Qualifier("testB")//如果没有为TestB指定Name，则默认的Bean名为testB,即Class名的首字母小写
//    @Qualifier("test2")//使用Qualifier来明确指定Bean名来进行匹配
    private ITest test2;//如果变量名test2与TestB注册到ApplicationContext中Name一致，也可以完成注入

    @Test
    public void testAutoWired() {
        String myNameA = testA.getMyName();
        assertThat(myNameA, is(TestA.class.getCanonicalName()));
        String myNameB = test2.getMyName();
        assertThat(myNameB, is(TestB.class.getCanonicalName()));
    }

}
