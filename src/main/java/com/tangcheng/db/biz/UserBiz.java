package com.tangcheng.db.biz;

import com.tangcheng.db.entity.CustomUserDetails;
import com.tangcheng.db.mapper.UserDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Created by tang.cheng on 2017/4/13.
 */
@Repository
public class UserBiz {

    @Autowired
    private UserDoMapper userDoMapper;

    @Cacheable(value = "user", key = "#username")
    public CustomUserDetails getUserByName(String username) {
        return userDoMapper.getUserByName(username);
    }


}
