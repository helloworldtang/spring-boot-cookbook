package com.tangcheng.app.service.biz;

import com.tangcheng.app.dao.biz.CityBiz;
import com.tangcheng.app.domain.entity.CityDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tang.cheng on 2017/4/26.
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityBiz cityBiz;

    @Override
    public List<CityDo> selectCity(String state) {
        return cityBiz.selectCity(state);
    }

    @Override
    public List<CityDo> selectAll() {
        List<CityDo> cityDos = cityBiz.selectAll();
        CityDo cityDo = cityDos.get(0);
        System.out.println(cityDo);
        return cityDos;
    }
}
