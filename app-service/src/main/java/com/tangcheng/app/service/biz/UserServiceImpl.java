package com.tangcheng.app.service.biz;

import com.tangcheng.app.dao.repository.UserRepository;
import com.tangcheng.app.domain.errorcode.GlobalCode;
import com.tangcheng.app.domain.vo.CustomUserDetails;
import com.tangcheng.app.domain.vo.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tang.cheng on 2017/4/13.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResultData<?> saveUser(String username, String email, String password) {
        userRepository.saveUser(username, email, password);
        return new ResultData<>(GlobalCode.SUCCESS);
    }

    @Override
    public ResultData<?> getUser(String username) {
        CustomUserDetails userDetails = userRepository.getUser(username);
        if (userDetails == null) {
            LOGGER.warn("invalid username:{}", username);
            return new ResultData<>(GlobalCode.NOT_EXIST);
        }
        return new ResultData<>(GlobalCode.SUCCESS, userDetails);
    }

    @Override
    public ResultData<?> updateUser(String username, String email) {
        userRepository.updateUser(username, email);
        return new ResultData<>(GlobalCode.SUCCESS);
    }

    @Override
    public ResultData<?> removeUser(String username) {
        userRepository.removeUser(username);
        return new ResultData<>(GlobalCode.SUCCESS);
    }

}
