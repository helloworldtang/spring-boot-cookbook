package com.tangcheng.app.rest.controller;

import com.tangcheng.app.domain.entity.CityDo;
import com.tangcheng.app.rest.aop.MethodLogAnnotation;
import com.tangcheng.app.service.biz.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tang.cheng on 2016/12/1.
 */
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @MethodLogAnnotation(desc = "查找City")
    @RequestMapping(value = "/city/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<CityDo>> selectState(@PathVariable("state") String state) {
        return ResponseEntity.ok(cityService.selectCity(state));
    }


    @MethodLogAnnotation(desc = "查找City")
    @RequestMapping(value = "/city/list", method = RequestMethod.GET)
    public ResponseEntity<List<CityDo>> selectAll() {
        return ResponseEntity.ok(cityService.selectAll());
    }

}
