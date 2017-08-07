package com.tangcheng.learning.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-08-07  18:59
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNameValid() {
        return StringUtils.isNoneBlank(name);
    }
}
