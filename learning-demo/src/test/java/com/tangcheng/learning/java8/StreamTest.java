package com.tangcheng.learning.java8;

import com.tangcheng.learning.json.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StreamTest {

    @Test
    public void joinList() {
        String expect = "1,2,3,4,5";
        List<String> source = Arrays.asList("1", "2", "3", "4", "5");
        String actual = source.stream().collect(Collectors.joining(","));
        assertThat(actual).isEqualTo(expect);
    }


    @Test
    public void joinListWithPO() {
        List<User> circleList = new ArrayList<User>() {
            {
                add(new User(1L, "1"));
                add(new User(2L, "2"));
                add(new User(5L, "5"));
                add(new User(3L, "3"));
            }
        };
        String expect = "1,2,5,3";
        String actual = circleList.stream().map(User::getName).collect(Collectors.joining(","));
        log.info("actual:{}", actual);
        assertThat(actual).isEqualTo(expect);
    }

}
