package com.tangcheng.app.dao.repository.mapper;

import com.tangcheng.app.domain.entity.CityDo;
import com.tangcheng.app.dao.tk.AppMapper;

import java.util.List;
import java.util.Map;

public interface CityDoMapper extends AppMapper<CityDo> {
    List<CityDo> testWhereLabel(Map<String, Object> paramsMap);
}