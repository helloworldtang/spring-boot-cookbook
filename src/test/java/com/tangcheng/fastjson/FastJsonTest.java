package com.tangcheng.fastjson;

/**
 * Created by tangcheng on 3/26/2017.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FastJsonTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(FastJsonTest.class);

    private static ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if (v == null)
                return "";
            return v;
        }
    };

    public static void main(String[] args) {
        FastJsonTest fastJsonTest = new FastJsonTest();
        fastJsonTest.objectScene();
//        fastJsonTest.pojoScene();
    }

    private void objectScene() {
        LOGGER.info("---------------------------OBJECT---------------------------");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhangSan");
        jsonObject.put("age", 13);
        jsonObject.put("isMale", true);
        jsonObject.put("gf", null);


        Map<String, Object> map = new HashMap<>();
        map.put("list", new ArrayList<>());
        map.put("listNull", null);
        String[] array = {"1", "2"};
        map.put("array", array);
        map.put("arrayNull", null);
        map.put("map", new HashMap<>());
        map.put("mapNull", null);
        map.put("String", "str type");
        map.put("stringNull", null);

        map.put("jsonObject", jsonObject);
        LOGGER.info("默认情况下fastjson会将null的字段过滤掉:{}", JSON.toJSONString(map));
        LOGGER.info("SerializerFeature没有将null转换成空字符串,返回null:{}", JSON.toJSONString(map,
                SerializerFeature.WRITE_MAP_NULL_FEATURES,
                SerializerFeature.WriteNonStringKeyAsString,
                SerializerFeature.WriteNullStringAsEmpty));

        LOGGER.info("下面两种写法等价：使用ValueFilter后，因为不需要使用SerializerFeature");
        LOGGER.info("ValueFilter可以将所有null转化成空字符串：{}", JSON.toJSONString(map, filter));
        LOGGER.info("ValueFilter可以将所有null转化成空字符串：{}", JSON.toJSONString(map, filter,
                SerializerFeature.WriteNonStringKeyAsString,
                SerializerFeature.WriteNullStringAsEmpty));

    }

    private void pojoScene() {
        System.out.println("---------------------------POJO---------------------------");
        Student zhangsan = new Student();
        zhangsan.setAge(13);
        zhangsan.setName("张三");
        zhangsan.setMale(true);
        Student student = new Student();
        student.setName(null);//为null的字符串
        student.setMale(false);
        student.setAge(11);
        zhangsan.setGf(student);
        LOGGER.info("默认情况下fastjson会将null的字段不返回:{}", JSON.toJSONString(zhangsan));
        LOGGER.info("针对POJO,WriteMapNullValue没有将null转换成空字符串:{}", JSON.toJSONString(zhangsan,
                SerializerFeature.WriteMapNullValue));

        LOGGER.info("针对POJO,WriteNullStringAsEmpty将null转换成空字符:{}", JSON.toJSONString(zhangsan,
                SerializerFeature.WriteNullStringAsEmpty));

        LOGGER.info("针对POJO,ValueFilter将null转换成空字符:{}", JSON.toJSONString(zhangsan, filter));
        LOGGER.info("针对POJO,ValueFilter将null转换成空字符:{}", JSON.toJSONString(zhangsan, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty));
    }

}


