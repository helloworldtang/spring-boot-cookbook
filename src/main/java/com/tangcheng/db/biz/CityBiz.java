package com.tangcheng.db.biz;

import com.tangcheng.db.entity.CityDo;
import com.tangcheng.db.mapper.CityDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tang.cheng on 2016/12/1.
 */
@Repository
public class CityBiz {

    @Autowired
    private CityDoMapper cityDoMapper;

    public List<CityDo> selectCity(String state) {
        CityDo cityDo = new CityDo();
        cityDo.setState(state);
        return cityDoMapper.select(cityDo);
    }


    public List<CityDo> selectAll() {
        return cityDoMapper.selectAll();
    }
}
