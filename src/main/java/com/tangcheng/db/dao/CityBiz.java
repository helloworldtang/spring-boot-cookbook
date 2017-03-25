package com.tangcheng.db.dao;

import com.tangcheng.db.entity.City;
import com.tangcheng.db.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tang.cheng on 2016/12/1.
 */
@Repository
public class CityBiz {
    @Autowired
    private CityMapper cityMapper;

    public List<City> selectCity(String state) {
        return cityMapper.findByState(state);
    }


}
