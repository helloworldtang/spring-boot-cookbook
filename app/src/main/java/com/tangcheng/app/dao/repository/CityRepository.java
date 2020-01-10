package com.tangcheng.app.dao.repository;

import com.tangcheng.app.domain.entity.CityDO;
import com.tangcheng.app.dao.repository.mapper.CityDOMapper;
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
    private CityDOMapper cityDoMapper;

    public List<CityDO> getCity(String state) {
        CityDO cityDo = new CityDO();
        cityDo.setState(state);
        return cityDoMapper.select(cityDo);
    }


    public List<CityDO> listAll() {
        return cityDoMapper.selectAll();
    }

    public List<CityDO> list(Long id, String name, String state) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("id", id);
        paramsMap.put("name", name);
        paramsMap.put("state", state);
        return cityDoMapper.testWhereLabel(paramsMap);
    }


}
