package com.tangcheng.app.web;

import com.tangcheng.app.service.biz.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String getLocationData() {
        return locationService.getLocationData();
    }

    @GetMapping("map.html")
    public String listMap() {
        return "map";
    }

}
