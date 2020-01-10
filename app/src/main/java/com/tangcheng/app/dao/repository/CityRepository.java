package com.tangcheng.app.dao.repository;

import com.tangcheng.app.dao.repository.mapper.CityDOMapper;
import com.tangcheng.app.domain.entity.CityDO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tang.cheng on 2016/12/1.
 */
@Repository
public class CityRepository {

    @Resource
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
