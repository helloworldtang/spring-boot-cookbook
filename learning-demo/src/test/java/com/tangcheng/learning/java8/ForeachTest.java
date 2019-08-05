/**
 * @Auther: cheng.tang
 * @Date: 2019/3/25
 * @Description:
 */
package com.tangcheng.learning.java8;

import org.junit.Test;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Auther: cheng.tang
 * @Date: 2019/3/25
 * @Description:
 */
public class ForeachTest {
    /**
     * 1
     * 3
     * 4
     */
    @Test
    public void testReturnWhenForeach() {
        Stream.of(1, 2, 3, 4).forEach(item -> {
            if (Objects.equals(item, 2)) {
//                break;
//                continue;
                return;
            }
            System.out.println(item);
        });

    }


}
