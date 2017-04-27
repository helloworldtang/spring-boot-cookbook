package com.tangcheng.app.dao.biz;

import com.tangcheng.app.domain.entity.CityDo;
import com.tangcheng.app.domain.mapper.CityDoMapper;
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

    public List<CityDo> getCity(String state) {
        CityDo cityDo = new CityDo();
        cityDo.setState(state);
        return cityDoMapper.select(cityDo);
    }


    public List<CityDo> listAll() {
        return cityDoMapper.selectAll();
    }
}
