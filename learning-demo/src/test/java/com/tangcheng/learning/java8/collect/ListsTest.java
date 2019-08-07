/**
 * @Auther: cheng.tang
 * @Date: 2019/8/7
 * @Description:
 */
package com.tangcheng.learning.java8.collect;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: cheng.tang
 * @date: 2019/8/7
 * @see
 * @since
 */
public class ListsTest {


    @Test
    public void testRemoveIf() {
        List<Integer> userTagList = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        System.out.println(userTagList);
        /**
         * 删除条件为true的
         */
        userTagList.removeIf(item -> item <= 3);
        System.out.println(userTagList);
        assertThat(userTagList).containsExactly(4, 5, 6);
    }


}
