package com.tangcheng.app.dao.repository.mapper;

import com.tangcheng.app.dao.tk.AppMapper;
import com.tangcheng.app.domain.entity.UserDo;
import com.tangcheng.app.domain.vo.CustomUserDetails;

public interface UserDoMapper extends AppMapper<UserDo> {

    CustomUserDetails getUserByName(String username);

}