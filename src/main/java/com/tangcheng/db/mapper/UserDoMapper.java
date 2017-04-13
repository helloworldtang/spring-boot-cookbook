package com.tangcheng.db.mapper;

import com.tangcheng.db.entity.CustomUserDetails;
import com.tangcheng.db.entity.UserDo;
import com.tangcheng.db.util.MyMapper;

public interface UserDoMapper extends MyMapper<UserDo> {
    CustomUserDetails getUserByName(String username);
}