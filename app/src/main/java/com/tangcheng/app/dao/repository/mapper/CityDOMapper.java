package com.tangcheng.app.dao.repository.mapper;

import com.tangcheng.app.domain.entity.CityDO;
import com.tangcheng.app.dao.tk.AppMapper;

import java.util.List;
import java.util.Map;

public interface CityDOMapper extends AppMapper<CityDO> {
    List<CityDO> testWhereLabel(Map<String, Object> paramsMap);
}