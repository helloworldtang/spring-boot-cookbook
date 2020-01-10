package com.tangcheng.app.dao.repository.mapper;

import com.tangcheng.app.dao.tk.AppMapper;
import com.tangcheng.app.domain.entity.UserDO;
import com.tangcheng.app.domain.vo.CustomUserDetails;

public interface UserDOMapper extends AppMapper<UserDO> {

    CustomUserDetails getUserByName(String username);

}