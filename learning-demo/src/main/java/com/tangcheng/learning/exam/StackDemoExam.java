package com.tangcheng.learning.exam;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/18/2019 11:12 PM
 */
public class StackDemoExam {

    private static final Map<Character, Character> patternMatch = new ConcurrentHashMap<>();

    static {
        patternMatch.put('(', ')');
        patternMatch.put('{', '}');
        patternMatch.put('[', ']');
    }

    /**
     * 1.  括号匹配验证
     * 一个字符串中，包括字符  ‘(‘,  ‘)’,  ‘{‘,  ‘}’,  ‘[‘,  ‘]’。
     * 要求写一个函数，验证字符串中这些括号是以正确的顺序匹配的。
     * 注意：(,  ),  [,  ],  {,  }可以互相嵌套。
     * 譬如："()"  和"()[]{}"是正确的，"(]"  and  "([)]"是不正确的
     *
     * @return
     */
    public boolean bracketMatch(String bracketSource) {
        if (StringUtils.isBlank(bracketSource)) {
            return false;
        }
        char[] charArray = bracketSource.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char next : charArray) {
            /**
             * public synchronized E peek() {
             *    int len = size();
             *      if (len == 0)
             *        throw new EmptyStackException();
             */
            if (stack.isEmpty()) {
                if (patternMatch.get(next) == null) {
                    // when empty,the next must be left bracket!
                    return false;
                }
                stack.push(next);
                continue;
            }

            Character lastItem = stack.peek();
            if (Objects.equals(next, patternMatch.get(lastItem))) {
                stack.pop();
            } else {
                stack.push(next);
            }
        }
        return stack.isEmpty();
    }


}
