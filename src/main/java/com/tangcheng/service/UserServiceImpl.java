package com.tangcheng.service;

import com.tangcheng.api.UserService;
import com.tangcheng.db.biz.UserBiz;
import com.tangcheng.db.entity.CustomUserDetails;
import com.tangcheng.global.domain.ResultData;
import com.tangcheng.global.exception.GlobalCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
    public ResultData<?> addUser(String username, String email, String password) {
        userBiz.addUser(username, email,password);
        return new ResultData<>(GlobalCode.SUCCESS);
    }

    @Override
    public ResultData<?> getUser(String username) {
        CustomUserDetails userDetails = userBiz.getUserByName(username);
        if (userDetails == null) {
            LOGGER.warn("invalid username:{}", username);
            return new ResultData<>(GlobalCode.NOT_EXIST);
        }
        return new ResultData<>(GlobalCode.SUCCESS, userDetails);
    }

    @Override
    public ResultData<?> putUser(String username, String email) {
        userBiz.putUser(username, email);
        return new ResultData<>(GlobalCode.SUCCESS);
    }

    @Override
    public ResultData<?> delUser(String username) {
        userBiz.delUser(username);
        return new ResultData<>(GlobalCode.SUCCESS);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails userDetails = userBiz.getUserByName(username);
        if (userDetails == null) {
            LOGGER.warn("{} not exist.", username);
            throw new UsernameNotFoundException(username + " not exists");
        }

        return new User(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAccountEnabled(),
                userDetails.generateAccountNonExpired(),
                userDetails.generateCredentialsNonExpired(),
                !userDetails.getAccountLocked(),
                userDetails.generateAuthorities());
    }

}
