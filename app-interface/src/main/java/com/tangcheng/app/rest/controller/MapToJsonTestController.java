package com.tangcheng.app.rest.controller;

import com.tangcheng.app.domain.query.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by tang.cheng on 2017/1/17.
 */
@RestController
@RequestMapping("test")
public class MapToJsonTestController {

    /**
     * 数组和List转换成json后，格式相同
     * map和POJO转换成json后，格式相同
     * {
     * "array": [
     * "array1",
     * "array2",
     * "array3",
     * "array4"
     * ],
     * "list": [
     * "1List",
     * "2List",
     * "3List"
     * ],
     * "map": {
     * "0": "str0",
     * "1": "str1",
     * "2": "str2",
     * "3": "str3",
     * "4": "str4"
     * },
     * "string": "str"
     * }
     *
     * @return
     */
    @RequestMapping(value = "json", method = RequestMethod.GET)
    public ResponseEntity<Test> test() {
        Test test = new Test();
        String[] array = {"array1", "array2", "array3", "array4"};
        test.setArray(array);
        test.setList(newArrayList("1List", "2List", "3List"));
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i, "str" + i);
        }
        test.setMap(map);
        test.setString("str");
        return ResponseEntity.ok(test);
    }

}

