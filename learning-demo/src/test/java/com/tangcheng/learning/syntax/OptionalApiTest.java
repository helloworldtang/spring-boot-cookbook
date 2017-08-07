package com.tangcheng.learning.syntax;

import com.tangcheng.learning.domain.User;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * spring-boot-cookbook
 * http://www.cnblogs.com/softidea/p/5873140.html
 *
 * @author : tang.cheng
 * @version : 2017-08-07  19:03
 */
public class OptionalApiTest {

    public static final String UNKNOWN = "Unknown";

    @Test
    public void testOneChain() {
        assertThat(getName(null), is(getNameUseOptional(null)));
        User user = new User();
        String actual = "name";
        user.setName(actual);
        assertThat(getName(user), is(getNameUseOptional(user)));
    }

    public static String getName(User user) {
        if (user == null) {
            return UNKNOWN;
        }
        return user.getName();
    }


    public static String getNameUseOptional(User user) {
        return Optional.ofNullable(user)
                .map(user1 -> user.getName())
                .orElse(UNKNOWN);
    }

}
