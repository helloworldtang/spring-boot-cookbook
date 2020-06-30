package com.tangcheng.learning.listener;

/**
 * @author: cheng.tang
 * @date: 2020/6/30
 * @see
 * @since
 */
public interface EventHandler<T> {

    Boolean support(T msg);

    Boolean support(String name, Integer type);

}
