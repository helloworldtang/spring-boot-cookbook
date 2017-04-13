package com.tangcheng.service;

import com.tangcheng.api.UserService;
import com.tangcheng.db.biz.UserBiz;
import com.tangcheng.db.entity.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by tang.cheng on 2017/4/13.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserBiz userBiz;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails userDetails = userBiz.getUserByName(username);
        if (userDetails == null) {
            LOGGER.warn("{} not exist.", username);
            throw new UsernameNotFoundException(username + " not exists!");
        }
        return userDetails;
    }
}
