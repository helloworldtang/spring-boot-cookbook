package com.tangcheng.app.api.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@RestController
@SuppressWarnings(value = "unchecked")
public class TestRedisTemplateOperation {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "String类型增加一个value", notes = "String类型增加一个value")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String set() {
        String key = "mykey";
        stringRedisTemplate.boundValueOps(key).increment(1);
        return key;
    }

    @ApiOperation(value = "读取一个String类型的数据", notes = "读取一个String类型的数据")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {
        String key = "mykey";
        //如果key不存在时，Jedis会返回空字符串（key中无值时，redis会删除这个key）
        return stringRedisTemplate.boundValueOps(key).get();
    }

    @ApiOperation(value = "获取一个List列表", notes = "获取一个List列表")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        String key = "myList";
        //如果key不存在时，Jedis会返回空集合（key中无值时，redis会删除这个key）。这种场景的返回值为"[]"
        return redisTemplate.boundListOps(key).range(0, -1).toString();
    }

    @ApiOperation(value = "往List中新增一个元素，leftPush", notes = "往List中新增一个元素，leftPush")
    @RequestMapping(value = "/setList/{element}", method = RequestMethod.GET)
    public void setList(@PathVariable("element") String element) {
        String key = "myList";
        redisTemplate.boundListOps(key).leftPush(element);
    }

    /**
     * [null, null, null, null, null]true;true;true;true;true;
     * hash不存在时的Operation是否为null:false
     * hash不存在，或hash存在但Key不存在时，删除时也不会抛异常
     *
     * @return
     */
    @ApiOperation(value = "获取一个Hash中存放的数据", notes = "获取一个Hash中存放的数据")
    @RequestMapping(value = "/getHash", method = RequestMethod.GET)
    public String getHash() {
        String key = "myHash";
        Collection collection = Arrays.asList(1, 2, 3, 4, 5);
        //如果Hash不存在时，Jedis会给每个field返回一个null;存在的field会返回相关的值
        //下例中会返回  [null, null, null, null, null]true;true;true;true;true;
        List list = redisTemplate.boundHashOps(key).multiGet(collection);
        String result = list.toString();
        for (Object value : list) {
            result = result + (value == null) + ";";
        }
        redisTemplate.delete(key);
        result = result + " \n hash不存在时的Operation是否为null:" + (redisTemplate.boundHashOps(key) == null);

        //删除hash中不存在的key时，是否会抛异常
        String tmpKey = "myHash2";
        redisTemplate.boundHashOps(tmpKey).delete("notExistKey");
        redisTemplate.boundHashOps(tmpKey).put("key2-hash", "value2-hash");
        redisTemplate.boundHashOps(tmpKey).delete("notExistKey");
        result = result + " \n hash不存在，或hash存在但Key不存在时，删除时也不会抛异常";
        return result;
    }

    @ApiOperation(value = "往一个Hash添加一个键值对", notes = "往一个Hash添加一个键值对")
    @RequestMapping(value = "/setHash/{field}/{value}", method = RequestMethod.GET)
    public void setHash(@PathVariable("field") String field, @PathVariable("value") String value) {
        String key = "myHash";
        //执行hmset myHash 1 value1后
        //mget的返回值为[value1, null, null, null, null]false;true;true;true;true;
        redisTemplate.boundHashOps(key).put(field, value);
    }

    /**
     * 存放在Redis中的值：
     * hash : {"keyEmptyArrayList":[],"key2":"key2","keyWithNullValue":null}
     */
    @ApiOperation(value = "往一个Hash中放不Java对象的情况", notes = "往一个Hash中放不Java对象的情况")
    @RequestMapping(value = "/set/hash/null", method = RequestMethod.GET)
    public void setHasWithNullValue() {
        String key = "myhashWithNull";
        redisTemplate.boundHashOps(key).put("keyWithNullValue", null);//在hash的value中会存放字符串“null”
        redisTemplate.boundHashOps(key).put("keyEmptyArrayList", new ArrayList<>());//在hash的value中会存放字符串“[]”
        redisTemplate.boundHashOps(key).put("key2", "key2");
    }


    @ApiOperation(value = "一个获取不同Redis数据结构值的通用功能", notes = "一个获取不同Redis数据结构值的通用功能")
    @RequestMapping(value = "/redis/{key}", method = RequestMethod.GET)
    public String getRedisData(@PathVariable("key") String key) throws JsonProcessingException {
        if (redisTemplate.hasKey(key)) {
            ObjectMapper objectMapper = new ObjectMapper();

            DataType dataType = redisTemplate.type(key);
            String type = dataType.code();
            if ("string".equals(type)) {
                Object _obj = redisTemplate.opsForValue().get(key);
                return MessageFormat.format("string : {0}", objectMapper.writeValueAsString(_obj));
            }
            if ("list".equals(type)) {
                Object _obj = redisTemplate.boundListOps(key).range(0, -1);
                return MessageFormat.format("list : {0}", objectMapper.writeValueAsString(_obj));
            }
            if ("set".equals(type)) {
                Object _obj = redisTemplate.boundSetOps(key).members();
                return MessageFormat.format("set : {0}", objectMapper.writeValueAsString(_obj));
            }
            if ("zset".equals(type)) {
                Object _obj = redisTemplate.boundZSetOps(key).range(0, -1);
                return MessageFormat.format("zset : {0}", objectMapper.writeValueAsString(_obj));
            }
            if ("hash".equals(type)) {
                Object _obj = redisTemplate.boundHashOps(key).entries();
                return MessageFormat.format("hash : {0}", objectMapper.writeValueAsString(_obj));
            }
            if ("none".equals(type)) {
                return "none：";
            }
        }
        return "no key";
    }

}