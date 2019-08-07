package com.tangcheng.app.service.biz;

import com.tangcheng.app.domain.entity.CityDo;

import java.util.List;

/**
 * Created by tang.cheng on 2017/4/26.
 */
public interface CityService {
    List<CityDo> getCity(String state);

    List<CityDo> listAll();
}
