package com.tangcheng.app.service.biz;

import com.tangcheng.app.domain.vo.GpsVO;
import com.tangcheng.app.domain.vo.MapVO;

import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng@xiaoyi.com
 * @version : 2017-06-09  19:55
 */
public interface LocationService {
    MapVO getLocationData();

    List<GpsVO> getGpsData();
}
