package com.tangcheng.learning.exam;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/18/2019 11:30 PM
 */
public class StackDemoExamTest {


    /**
     * 1.  括号匹配验证
     * 一个字符串中，包括字符  ‘(‘,  ‘)’,  ‘{‘,  ‘}’,  ‘[‘,  ‘]’。
     * 要求写一个函数，验证字符串中这些括号是以正确的顺序匹配的。
     * 注意：(,  ),  [,  ],  {,  }可以互相嵌套。
     * 譬如："()"  和"()[]{}"是正确的，"(]"  and  "([)]"是不正确的
     *
     * @return
     */
    @Test
    public void bracketMatch() {
        StackDemoExam demoExam = new StackDemoExam();
        String demo1 = "()[]{}";
        boolean result = demoExam.bracketMatch(demo1);
        assertThat(result).isTrue();
        System.out.println(result);

        demo1 = "()[()]{[]}";
        result = demoExam.bracketMatch(demo1);
        assertThat(result).isTrue();
        System.out.println(result);
        demo1 = "()";
        result = demoExam.bracketMatch(demo1);
        assertThat(result).isTrue();
        System.out.println(result);
        String demo2 = "([)]";
        result = demoExam.bracketMatch(demo2);
        assertThat(result).isFalse();
        System.out.println(result);
        demo2 = "(]";
        result = demoExam.bracketMatch(demo2);
        assertThat(result).isFalse();
        System.out.println(result);
    }
}