package com.tangcheng.app.domain.vo;

import java.io.Serializable;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng@xiaoyi.com
 * @version : 2017-06-09  20:00
 */
public class GeoVO implements Serializable{
    private String name;
    private Long value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
