package com.tangcheng.learning.java8;

import com.tangcheng.learning.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

/**
 * spring-boot-cookbook
 * https://juejin.im/entry/5b21e80de51d455c6e0adbca?utm_medium=be&utm_source=weixinqun
 *
 * @author tangcheng
 * @date 6/17/2018 4:22 PM
 */
@Slf4j
public class OptionalTest {

    /**
     * orElse(T other)，
     * orElseGet(Supplier<? extends T> other)
     * 的区别：
     * 当user值不为null时，orElse函数依然会执行createUser()方法，
     * 而orElseGet函数并不会执行createUser()方法
     */
    @Test
    public void should_invoke_createUser_when_input_null() {
        User user = null;
        user = Optional.ofNullable(user).orElseGet(this::createUser);
        log.info("after orElseGet(this::createUser) ,user: {}", user);

        /**
         * 不管user是不是null，orElse后面的createUser()方法都会被调用
         */
        user = Optional.ofNullable(user).orElse(createUser());
        log.info("after orElse(createUser()) ,user: {}", user);
        /**
         *如果User不是null,则osElseGet后面的方法不会被调用
         */
        user = Optional.ofNullable(user).orElseGet(this::createUser);
        log.info("when user is not null. after orElseGet, user: {}", user);
    }

    private User createUser() {
        log.info("invoke createUser()");
        return User.builder().name("optional").build();
    }

    /**
     * orElseThrow，就是value值为null时,直接抛一个异常出去
     */
    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_user_is_null() {
        User user = null;
        Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException("不能输入null"));
    }


    /**
     * map(Function<? super T, ? extends U> mapper)
     * 和
     * flatMap(Function<? super T, Optional<U>> mapper)
     */
    @Test
    public void should_map() {
        User user = User.builder().name("hello").age((byte) 12).build();
        String name = Optional.ofNullable(user).map(User::getName).get();
        log.info("name:{}", name);
    }

    /**
     * filter 方法接受一个 Predicate 来对 Optional 中包含的值进行过滤，如果包含的值满足条件，那么还是返回这个 Optional；
     * 否则返回 Optional.empty
     */
    @Test
    public void should_filter() {
        User user = User.builder().name("hello").age((byte) 12).build();
        Optional<User> userOptional = Optional.ofNullable(user).filter(u -> u.getName().length() < 6);
        log.info("userOptional:{}", userOptional.get());
    }

    /**
     * isPresent即判断value值是否为空，
     * 而ifPresent就是在value值不为空时，做一些操作
     */
    @Test
    public void should_is_present() {
        User user = User.builder().name("hello").age((byte) 12).build();
        Optional.ofNullable(user).ifPresent(u -> {
            log.info("u:{}", u);
        });
    }

    /**
     *
     */
    @Test
    public void test_should_city() throws Exception {
        User user = User.builder().name("hello").age((byte) 12).build();
        Optional.ofNullable(user)
                .map(User::getName)
                .orElseThrow(() -> new Exception("取指错误"));
    }


}
