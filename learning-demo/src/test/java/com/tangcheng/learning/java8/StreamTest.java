package com.tangcheng.learning.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StreamTest {


    /**
     * Filter 只返回true的记录
     */
    @Test
    public void filterTest() {
        List<String> listOne = Stream.of("1", "2", "3", "4").collect(Collectors.toList());
        List<String> listTwo = Stream.of("2", "3", "5").collect(Collectors.toList());
        listTwo.removeAll(listOne);
        assertThat(listTwo.size()).isEqualTo(1);
        assertThat(listTwo.get(0)).isEqualTo("5");
        listOne.addAll(listTwo);
        assertThat(listOne.size()).isEqualTo(5);


        List<String> list = listOne.stream().filter("2"::equals).collect(Collectors.toList());
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0)).isEqualTo("2");

    }


    /**
     * list排序
     */
    @Test
    public void listSort() {
        //对数字进行排序
        List<Integer> numList = Arrays.asList(3, 1, 5, 2, 9, 8, 4, 10, 6, 7);
        numList.sort(Comparator.reverseOrder()); //reverseOrder倒序
        System.out.println("倒序:" + numList);//倒序:[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

        numList.sort(Comparator.naturalOrder());//naturalOrder自然排序即：正序
        System.out.println("正序:" + numList);//正序:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


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

    @Test
    public void testFlatMap() {
        String[] arrayOfWords = {"hello", "word"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        /**
         * Arrays.stream可以接受一个数组，让数组中的元素拼接成一个字符串流，而不是一个数组流
         * hello
         * word
         */
        streamOfWords.forEach(System.out::println);
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        /**
         * 流支持map方法，它会接受一个函数作为参数。
         * 这个函数会被应用到每个元素上，并将其映射成一个新的元素
         * （使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一个新版本”而不是去“修改”）
         * 下面的示例中把方法引用String::length传给了map方法，来提取流中的字符串的长度
         */
        List<Integer> resultList = words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(resultList);
        assertThat(resultList).isEqualTo(Arrays.asList(6, 7, 2, 6));

        List<String[]> resultArrayList = words.stream().map(word -> word.split(" ")).distinct().collect(Collectors.toList());
        /**
         * 数组
         * [Java, 8]
         * [Lambdas]
         * [In]
         * [Action]
         */
        resultArrayList.stream().map(Arrays::toString).forEach(System.out::println);
        /**
         *数组
         * [Java, 8]
         * [Lambdas]
         * [In]
         * [Action]
         */
        resultArrayList.forEach(item -> {
            System.out.println(Arrays.toString(item));
        });

        List<Stream<String>> collect = words.stream().map(word -> word.split(" ")).map(Arrays::stream).distinct().collect(Collectors.toList());
        /**
         * 已经是字符串，不再是数组
         * Java
         * 8
         * Lambdas
         * In
         * Action
         */
        collect.forEach(item -> {
            item.forEach(System.out::println);
        });


        List<String> stringList = words.stream().map(w -> w.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(stringList);
        assertThat(stringList).isEqualTo(Arrays.asList("Java", "8", "Lambdas", "In", "Action"));


    }

}

@Data
@AllArgsConstructor
class UserDemo {
    private Long id;
    private String name;
}
