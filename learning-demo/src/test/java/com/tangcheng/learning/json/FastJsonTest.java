package com.tangcheng.learning.json;

import com.alibaba.fastjson.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tangcheng on 7/9/2017.
 */
public class FastJsonTest {

    @Test
    public void should_return_null_when_given_null() {

        Object objectNull = null;
        assertThat(objectNull).isNull();
        String jsonString = JSON.toJSONString(objectNull);

        System.out.println("will be false:" + Objects.equals(jsonString, null));
        System.out.println("will be false:" + StringUtils.isBlank(jsonString));

//        assertThat("is string with 'null'", "null", is(jsonString));
        assertThat(jsonString).isEqualTo("null");
    }


    @Test
    public void testJsonPath() throws IOException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/JsonPathTestData.json");
        String jsonStr = IOUtils.toString(resourceAsStream, "utf-8");
        System.out.println(jsonStr);

        System.out.println(jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        System.out.println("\nBook数目：" + JSONPath.eval(jsonObject, "$.store.book.size()"));
        System.out.println("第一本书title：" + JSONPath.eval(jsonObject, "$.store.book[0].title"));
        System.out.println("price大于10元的book：" + JSONPath.eval(jsonObject, "$.store.book[price > 10]"));
        System.out.println("price大于10元的title：" + JSONPath.eval(jsonObject, "$.store.book[price > 10][0].title"));
        System.out.println("category(类别)为fiction(小说)的book：" + JSONPath.eval(jsonObject, "$.store.book[category = 'fiction']"));
        System.out.println("bicycle的所有属性值" + JSONPath.eval(jsonObject, "$.store.bicycle.*"));
        System.out.println("bicycle的color和price属性值" + JSONPath.eval(jsonObject, "$.store.bicycle['color','price']"));
    }


    @Test
    public void testGenerics() {
        Result<UserDO> result = new Result<>();
        result.setMsg("Success");
        List<UserDO> users = new ArrayList<>();
        users.add(new UserDO(1L, "Name1"));
        users.add(new UserDO(2L, "Name2"));
        result.setModule(users);
        String js = JSON.toJSONString(result);
        System.out.println(js);//{"module":[{"id":1,"name":"Name1"},{"id":2,"name":"Name2"}],"msg":"Success"}
        //java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com.tangcheng.learning.json.User
//        Result<User> obj = (Result<User>) JSON.parseObject(js, Result.class); //v1.4.9中解决此错误
        Result<User> userResult = JSON.parseObject(js, new TypeReference<Result<User>>() {
        });
        System.out.println(userResult);//Result{msg='Success', module=[{"id":1,"name":"Name1"}, {"id":2,"name":"Name2"}]}
        System.out.println(JSON.toJSONString(userResult));//{"module":[{"id":1,"name":"Name1"},{"id":2,"name":"Name2"}],"msg":"Success"}
    }


    @Test
    public void serialMapToJson() {
        Map<Long, String> source = new HashMap<>();
        for (long i = 0; i < 10; i++) {
            source.put(i, "key" + i);
        }

        /**
         * {0:"key0",1:"key1",2:"key2",3:"key3",4:"key4",5:"key5",6:"key6",7:"key7",8:"key8",9:"key9"}
         */
        String result = JSON.toJSONString(source, true);
        System.out.println(result);
    }


    @Test
    public void serialMapWithPOKey() {
        Map<UserDO, UserDO> source = new HashMap<>();
        for (long i = 0; i < 10; i++) {
            source.put(new UserDO(i, "name" + i), new UserDO(i, "name" + i));
        }
        String jsonStr = JSON.toJSONString(source);
        System.out.println(jsonStr);
    }


    @Test
    public void serialArrayToJson() {
        String[] urls = {"http://1.com", "http://2.com"};
        /**
         * ["http://1.com","http://2.com"]
         */
        String result = JSON.toJSONString(urls, true);
        assertThat(result).isEqualTo("[\"http://1.com\",\"http://2.com\"]");
        System.out.println(result);

        List<String> urlList = new ArrayList<>();
        urlList.add("http://1.com");
        urlList.add("http://2.com");
        /**
         [
         "http://1.com",
         "http://2.com"
         ]
         */
        result = JSON.toJSONString(urlList, true);//prettyFormat
        assertThat(result).isEqualTo("[\n" +
                "\t\"http://1.com\",\n" +
                "\t\"http://2.com\"\n" +
                "]");
        System.out.println(result);

        List<Integer> integers = JSON.parseArray("", Integer.class);
        System.out.println(integers);//null
//        assertThat(integers, is(nullValue()));  //org.hamcrest.MatcherAssert.assertThat
        assertThat(integers).isNull();
        integers = JSON.parseArray("[]", Integer.class);
        System.out.println(integers);//[]
        assertThat(integers).isNotNull().isEmpty();

//        assertThat(integers.size(), is(0)); //org.hamcrest.MatcherAssert.assertThat
        assertThat(integers.size()).isEqualTo(0);
        JSONArray objects = new JSONArray();
        System.out.println(objects.toJSONString());//[]
//        assertThat(integers.size(), is(0)); //org.hamcrest.MatcherAssert.assertThat
        assertThat(integers.size()).isEqualTo(0);
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class UserDO {
    private Long id;
    private String name;
}
