package com.tangcheng.app.dao.repository;

import com.tangcheng.app.domain.entity.CityDo;
import com.tangcheng.app.domain.mapper.CityDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tang.cheng on 2016/12/1.
 */
@Repository
public class CityRepository {

    @Autowired
    private CityDoMapper cityDoMapper;

    public List<CityDo> getCity(String state) {
        CityDo cityDo = new CityDo();
        cityDo.setState(state);
        return cityDoMapper.select(cityDo);
    }


    public List<CityDo> listAll() {
        return cityDoMapper.selectAll();
    }

    public List<CityDo> list(Long id, String name, String state) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("id", id);
        paramsMap.put("name", name);
        paramsMap.put("state", state);
        return cityDoMapper.testWhereLabel(paramsMap);
    }


}
