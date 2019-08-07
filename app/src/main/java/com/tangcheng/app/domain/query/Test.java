package com.tangcheng.app.domain.query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Test implements Serializable {
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