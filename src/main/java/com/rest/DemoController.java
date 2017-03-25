package com.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by tang.cheng on 2017/1/17.
 */
@RestController
public class DemoController {

    /**
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
    @RequestMapping(value = "/test/json", method = RequestMethod.GET)
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

class Test implements Serializable {
    private String[] array;
    private List<String> list;
    private Map<Integer, String> map;
    private String string;

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}