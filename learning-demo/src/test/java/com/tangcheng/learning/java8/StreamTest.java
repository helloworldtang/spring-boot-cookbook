package com.tangcheng.learning.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
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
        List<UserDemo> circleList = new ArrayList<UserDemo>() {
            {
                add(new UserDemo(1L, "1"));
                add(new UserDemo(2L, "2"));
                add(new UserDemo(5L, "5"));
                add(new UserDemo(3L, "3"));
            }
        };
        String expect = "1,2,5,3";
        String actual = circleList.stream().map(UserDemo::getName).collect(Collectors.joining(","));
        log.info("actual:{}", actual);
        assertThat(actual).isEqualTo(expect);
    }

}

@Data
@AllArgsConstructor
class UserDemo {
    private Long id;
    private String name;
}
