/**
 * @Auther: cheng.tang
 * @Date: 2019/4/16
 * @Description:
 */
package com.tangcheng.learning.java8;

import org.junit.Test;

import java.util.*;

/**
 * @Auther: cheng.tang
 * @Date: 2019/4/16
 * @Description:
 */
public class ForeachBreakAndContinueTest {


    @Test
    public void testContinue() {
        List<Integer> old = Arrays.asList(1, 2, 3, 4);
        List<Integer> newList = Collections.singletonList(2);
        List<Integer> needToAdd = new ArrayList<>();
        outLoop:
        for (Integer oldId : old) {
            for (Integer newId : newList) {
                if (Objects.equals(oldId, newId)) {
                    continue outLoop;
                }
            }
            needToAdd.add(oldId);
        }
        /**
         * [1, 3, 4]
         */
        System.out.println(needToAdd);
    }


    @Test
    public void testBreak() {
        List<Integer> old = Arrays.asList(1, 2, 3, 4);
        List<Integer> newList = Collections.singletonList(2);
        List<Integer> needToAdd = new ArrayList<>();
        outLoop:
        for (Integer oldId : old) {
            for (Integer newId : newList) {
                if (Objects.equals(oldId, newId)) {
                    break outLoop;
                }
            }
            needToAdd.add(oldId);
        }
        /**
         * [1]
         */
        System.out.println(needToAdd);

    }


}
