package com.tangcheng.app.domain.mapper;

import com.tangcheng.app.domain.entity.CityDo;
import com.tangcheng.app.domain.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface CityDoMapper extends MyMapper<CityDo> {
    List<CityDo> testWhereLabel(Map<String, Object> paramsMap);
}