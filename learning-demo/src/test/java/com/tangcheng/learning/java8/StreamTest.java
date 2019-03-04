package com.tangcheng.learning.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StreamTest {

    /**
     * list排序
     */
    @Test
    public void listSort() {
        //对数字进行排序
        List<Integer> nums = Arrays.asList(3, 1, 5, 2, 9, 8, 4, 10, 6, 7);
        nums.sort(Comparator.reverseOrder()); //reverseOrder倒序
        System.out.println("倒序:" + nums);//倒序:[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

        nums.sort(Comparator.naturalOrder());//naturalOrder自然排序即：正序
        System.out.println("正序:" + nums);//正序:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


        //按照对象某个属性进行排序：例如年龄
        List<UserDemo> users = new ArrayList<>();
        users.add(new UserDemo(22L, "name_22"));
        users.add(new UserDemo(18L, "name_18"));
        users.add(new UserDemo(35L, "name_35"));
        users.add(new UserDemo(16L, "name_16"));
        users.add(new UserDemo(40L, "name_40"));

        /**
         * o1,o2表示需要比较的对象，
         * 正序：o1.getAge().compareTo(o2.getAge())
         * 倒序：o2.getAge().compareTo(o1.getAge())
         **/
        users.sort(Comparator.comparing(UserDemo::getId));
        /**
         * age正序:[UserDemo(id=16, name=name_16), UserDemo(id=18, name=name_18), UserDemo(id=22, name=name_22), UserDemo(id=35, name=name_35), UserDemo(id=40, name=name_40)]
         */
        System.err.println("age正序:" + users);
        users.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
        /**
         * age倒序:[UserDemo(id=40, name=name_40), UserDemo(id=35, name=name_35), UserDemo(id=22, name=name_22), UserDemo(id=18, name=name_18), UserDemo(id=16, name=name_16)]
         */
        System.err.println("age倒序:" + users);
    }

    /**
     * toMap
     * reduce
     */
    @Test
    public void listToMap() {
        long length = 10L;
        List<UserDemo> list = new ArrayList<>((int) length);
        for (long i = 0L; i < length; i++) {
            list.add(new UserDemo(i, "name_" + i));
        }
        String name = "name_" + 6;
        list.add(new UserDemo(6666L, name));

        /**
         * (k1, k2) -> k1
         * 后面重复的会被丢弃
         */
        Map<String, Long> nameToId = list.stream().collect(Collectors.toMap(UserDemo::getName, UserDemo::getId, (oldValue, newValue) -> oldValue));
        assertThat(nameToId.get(name)).isEqualTo(6L);
        Long sum = nameToId.values().parallelStream().reduce(0L, Long::sum);
        assertThat(sum).isEqualTo(1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9);
        /**
         * (k1, k2) -> k2
         * 后面的覆盖前面的
         */
        nameToId = list.stream().collect(Collectors.toMap(UserDemo::getName, UserDemo::getId, (oldValue, newValue) -> newValue));
        assertThat(nameToId.get(name)).isEqualTo(6666L);
        sum = nameToId.values().parallelStream().reduce(0L, Long::sum);
        assertThat(sum).isEqualTo(1 + 2 + 3 + 4 + 5 + 6666 + 7 + 8 + 9);
    }


    @Test
    public void joinList() {
        String expect = "1,2,3,4,5";
        List<String> source = Arrays.asList("1", "2", "3", "4", "5");
//        String actual = source.stream().collect(Collectors.joining(","));
        String actual = String.join(",", source);
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
