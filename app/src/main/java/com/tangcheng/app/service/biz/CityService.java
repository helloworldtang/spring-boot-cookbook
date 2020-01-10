package com.tangcheng.app.service.biz;

import com.tangcheng.app.domain.entity.CityDO;

import java.util.List;

/**
 * Created by tang.cheng on 2017/4/26.
 */
public interface CityService {
    List<CityDO> getCity(String state);

    List<CityDO> listAll();
}
