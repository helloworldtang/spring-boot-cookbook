package com.tangcheng.learning.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class Response<T> {
    private T data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {

        String jsonText = "{\"data\":{\"userId\":1,\"userName\":\"张三\"}}";
        Response r = JSON.parseObject(jsonText, new ParameterizedTypeImpl(new Class[]{User.class}, null, Response.class));
        /**
         * {"data":{"userId":1,"userName":"张三"}}
         */
        System.out.println(r);

        String jsonText2 = "{\"data\":{\"productId\":12,\"productName\":\"英语四六级甩卖\"}}";
        Response r2 = JSON.parseObject(jsonText2, new ParameterizedTypeImpl(new Class[]{Product.class}, null, Response.class));
        /**
         * {"data":{"productId":12,"productName":"英语四六级甩卖"}}
         */
        System.out.println(r2);
    }
}

@Setter
@Getter
class Product {
    private Long productId;
    private String productName;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

@Setter
@Getter
class User {
    private Integer userId;
    private String userName;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}