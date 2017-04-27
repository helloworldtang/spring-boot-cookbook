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
    public List<CityDo> getCity(String state) {
        return cityBiz.getCity(state);
    }

    @Override
    public List<CityDo> listAll() {
        List<CityDo> cityDoList = cityBiz.listAll();
        CityDo cityDo = cityDoList.get(0);
        System.out.println(cityDo);
        return cityDoList;
    }
}
