package com.tangcheng.learning.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import lombok.Data;

@Data
public class Response<T> {
    private T data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        String jsonText = "{\"data\":{\"id\":1,\"name\":\"张三\"}}";
        Response<User> response = JSON.parseObject(jsonText, new ParameterizedTypeImpl(new Class[]{User.class}, null, Response.class));
        /**
         * {"data":{"id":1,"name":"张三"}}
         */
        System.out.println(response);

        response = JSON.parseObject(jsonText, new TypeReference<Response<User>>() {
        });
        /**
         * {"data":{"id":1,"name":"张三"}}
         */
        System.out.println(response);
    }
}

@Data
class User {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}