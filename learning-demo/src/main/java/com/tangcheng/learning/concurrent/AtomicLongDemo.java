package com.tangcheng.learning.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by tangcheng on 5/21/2017.
 */
public class AtomicLongDemo {

    public static void main(String[] args) {
        AtomicLong seedUniquifier = new AtomicLong(10);
        /**
         * expected是用来校验是否可以进行赋值操作的。
         * 如果expected和seedUniquifier的值相同，则说明可以进行赋值操作，即进行赋值并且返回true
         * 如果返回false，则说明seedUniquifier的值已经被更改，不具备赋值的条件，当然seedUniquifier的值不会被此次操作改变。
         * 譬如下面的示例：
         * false
         10
         init==> current:10 next:1817834972766529810
         result==> true current:1817834972766529810 next:1817834972766529810
         init==> current:1817834972766529810 next:1493799903464559546
         */
        boolean result = seedUniquifier.compareAndSet(1, 2);
        System.out.println(result);
        System.out.println(seedUniquifier.get());
        for (int i = 0; i < 100; i++) {
            long current = seedUniquifier.get();
            long next = current * 181783497276652981L;
            System.out.println("init==> current:" + current + " next:" + next);
            boolean b = seedUniquifier.compareAndSet(current, next);
            System.out.println("result==> " + b + " current:" + seedUniquifier.get() + " next:" + next);
        }
    }

}
