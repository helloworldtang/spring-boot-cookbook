package com.tangcheng.learning.domain;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-08-07  19:00
 */
public class Competition {
    private CompResult compResult;

    public CompResult getCompResult() {
        return compResult;
    }

    public void setCompResult(CompResult compResult) {
        this.compResult = compResult;
    }
}

class CompResult{
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
