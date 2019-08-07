package com.tangcheng.app.api.web;

import com.tangcheng.app.domain.vo.GpsVO;
import com.tangcheng.app.domain.vo.MapVO;
import com.tangcheng.app.service.biz.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng@xiaoyi.com
 * @version : 2017-06-09  19:51
 */
@Controller
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("data.json")
    @ResponseBody
    public MapVO getLocationData() {
        return locationService.getLocationData();
    }

    @GetMapping("map.html")
    public String listMap() {
        return "map";
    }


    @GetMapping("amap.html")
    public String listGPSTrack() {
        return "amap";
    }


    @GetMapping("amapGps.json")
    @ResponseBody
    public List<GpsVO> amapData() {
        return locationService.getGpsData();
    }


}
