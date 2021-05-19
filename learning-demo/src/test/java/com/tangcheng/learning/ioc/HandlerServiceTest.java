package com.tangcheng.learning.ioc;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2021/4/20 3:12 下午
 * @see
 * @since
 */
class HandlerServiceTest {

    @Test
    public void testOrder() {
        List<Handler> list = new ArrayList<>();
        list.add(new Handler2Impl());
        list.add(new Handler1Impl());
        for (Handler handler : list) {
            System.out.println(handler.getClass());
        }
        AnnotationAwareOrderComparator.sort(list);
        for (Handler handler : list) {
            System.out.println(handler.getClass());
        }
    }

    public static void main(String[] args) {
        List<Handler> list = new ArrayList<>();
        list.add(new Handler2Impl());
        list.add(new Handler1Impl());
        for (Handler handler : list) {
            System.out.println(handler.getClass());
        }
        AnnotationAwareOrderComparator.sort(list);
        for (Handler handler : list) {
            System.out.println(handler.getClass());
        }
    }


}