package com.tangcheng.learning.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2021/6/9 4:34 下午
 * @see
 * @since
 */
@Slf4j
public class ModelToMapUtil {

    public static <T> Map<String, Object> transfer2Map(T model) {
        String jsonStr = JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        return transferJSONObject2Map(jsonObject);
    }


    private static Map<String, Object> transferJSONObject2Map(JSONObject jsonObject) {
        Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> result = new HashMap<>(entrySet.size());
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JSONArray) {
                List<Object> objects = transferJSONArray2Map((JSONArray) value);
                result.put(key, objects);
            } else if (value instanceof JSONObject) {
                Map<String, Object> jsonObject2Map = transferJSONObject2Map((JSONObject) value);
                result.put(key, jsonObject2Map);
            } else {
                result.put(key, value);
            }
        }
        return result;
    }


    private static List<Object> transferJSONArray2Map(JSONArray jsonArray) {
        List<Object> arrayList = new ArrayList<>(jsonArray.size());
        for (Object obj : jsonArray) {
            if (obj instanceof JSONObject) {
                Map<String, Object> jsonObject2Map = transferJSONObject2Map((JSONObject) obj);
                arrayList.add(jsonObject2Map);
            } else if (obj instanceof JSONArray) {
                List<Object> objects = transferJSONArray2Map((JSONArray) obj);
                arrayList.add(objects);
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }


}
