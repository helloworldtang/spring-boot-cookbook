package com.tangcheng.app.domain.mapper;

import com.tangcheng.app.domain.vo.CustomUserDetails;
import com.tangcheng.app.domain.entity.UserDo;
import com.tangcheng.app.domain.util.MyMapper;

public interface UserDoMapper extends MyMapper<UserDo> {
    CustomUserDetails getUserByName(String username);
}