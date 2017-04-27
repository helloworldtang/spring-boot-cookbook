package com.tangcheng.app.service.biz;

import com.tangcheng.app.dao.biz.UserBiz;
import com.tangcheng.app.domain.errorcode.GlobalCode;
import com.tangcheng.app.domain.vo.CustomUserDetails;
import com.tangcheng.app.domain.vo.ResultData;
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
    public ResultData<?> saveUser(String username, String email, String password) {
        userBiz.saveUser(username, email, password);
        return new ResultData<>(GlobalCode.SUCCESS);
    }

    @Override
    public ResultData<?> getUser(String username) {
        CustomUserDetails userDetails = userBiz.getUser(username);
        if (userDetails == null) {
            LOGGER.warn("invalid username:{}", username);
            return new ResultData<>(GlobalCode.NOT_EXIST);
        }
        return new ResultData<>(GlobalCode.SUCCESS, userDetails);
    }

    @Override
    public ResultData<?> updateUser(String username, String email) {
        userBiz.updateUser(username, email);
        return new ResultData<>(GlobalCode.SUCCESS);
    }

    @Override
    public ResultData<?> removeUser(String username) {
        userBiz.removeUser(username);
        return new ResultData<>(GlobalCode.SUCCESS);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails userDetails = userBiz.getUser(username);
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
