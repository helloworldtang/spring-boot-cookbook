package com.tangcheng.learning.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 * Created by tangcheng on 7/9/2017.
 */
public class FastJsonTest {

    @Test
    public void toJSONString() {

        Object objectNull = null;
        assertThat("is null", null, isEmptyOrNullString());
        String jsonString = JSON.toJSONString(objectNull);

        System.out.println("will be false:" + Objects.equals(jsonString, null));
        System.out.println("will be false:" + StringUtils.isBlank(jsonString));

        assertThat("is string with 'null'", "null", is(jsonString));

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
        Result<UserDemo> result = new Result<>();
        result.setMsg("Success");
        List<UserDemo> users = new ArrayList<>();
        users.add(new UserDemo(1L, "Name1"));
        users.add(new UserDemo(2L, "Name2"));
        result.setModule(users);
        String js = JSON.toJSONString(result);
        System.out.println(js);
        //java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com.tangcheng.learning.json.User
//        Result<User> obj = (Result<User>) JSON.parseObject(js, Result.class);
        Result<User> userResult = JSON.parseObject(js, new TypeReference<Result<User>>() {
        });
        System.out.println(userResult);
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
    public void serialArrayToJson() {
        String[] urls = {"http://1.com", "http://2.com"};
        /**
         * ["http://1.com","http://2.com"]
         */
        String result = JSON.toJSONString(urls, true);
        System.out.println(result);

        /**
         [
         "http://1.com",
         "http://2.com"
         ]
         */
        List<String> urlList = new ArrayList<>();
        urlList.add("http://1.com");
        urlList.add("http://2.com");
        result = JSON.toJSONString(urlList, true);
        System.out.println(result);
    }


}

@Data
@AllArgsConstructor
class UserDemo {
    private Long id;
    private String name;
}
