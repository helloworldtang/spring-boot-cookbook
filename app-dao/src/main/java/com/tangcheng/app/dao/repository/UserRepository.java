package com.tangcheng.app.dao.repository;

import com.tangcheng.app.domain.vo.CustomUserDetails;
import com.tangcheng.app.domain.entity.UserDo;
import com.tangcheng.app.domain.entity.UserRoleDo;
import com.tangcheng.app.domain.mapper.UserDoMapper;
import com.tangcheng.app.domain.mapper.UserRoleDoMapper;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by tang.cheng on 2017/4/13.
 */
@Repository
public class UserRepository {

    @Autowired
    private UserDoMapper userDoMapper;

    @Autowired
    private UserRoleDoMapper userRoleDoMapper;

    @Cacheable(value = "user", key = "#username")
    public CustomUserDetails getUser(String username) {
        return userDoMapper.getUserByName(username);
    }

    @Cacheable(value = "user", key = "#username")
    public CustomUserDetails saveUser(String username, String email, String password) {
        UserDo userDo = new UserDo();
        userDo.setUsername(username);
        userDo.setPassword(password);
        userDo.setEmail(email);
        userDo.setAccountEnabled(true);
        Date accountExpired = LocalDateTime.now().plusMonths(6).toDate();
        userDo.setAccountExpired(accountExpired);
        userDo.setCredentialsExpired(accountExpired);
        userDo.setAccountLocked(false);
        userDoMapper.insertUseGeneratedKeys(userDo);
        UserRoleDo userRoleDo = new UserRoleDo();
        userRoleDo.setUserId(userDo.getId());
        userRoleDo.setRoleId(2);//user
        userRoleDoMapper.insert(userRoleDo);
        return userDoMapper.getUserByName(username);
    }

    @CachePut(value = "user", key = "#username")
    public CustomUserDetails updateUser(String username, String email) {
        UserDo record = new UserDo();
        record.setUsername(username);
        CustomUserDetails userDetails = getUser(username);
        userDetails.setEmail(email);
        userDoMapper.updateByPrimaryKeySelective(userDetails);
        return userDetails;
    }

    @CacheEvict(value = "user", key = "#username")
    public void removeUser(String username) {
        UserDo record = new UserDo();
        record.setUsername(username);
        userDoMapper.delete(record);
    }

}
