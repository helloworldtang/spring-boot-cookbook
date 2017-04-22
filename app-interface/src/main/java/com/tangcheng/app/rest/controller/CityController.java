package com.tangcheng.app.rest.controller;

import com.tangcheng.app.dao.biz.CityBiz;
import com.tangcheng.app.domain.entity.CityDo;
import com.tangcheng.app.rest.aop.MethodLogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private CityBiz cityBiz;

    @MethodLogAnnotation(desc = "查找City")
    @RequestMapping(value = "/city/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<CityDo>> selectState(@PathVariable("state") String state) {
        List<CityDo> cityList = cityBiz.selectCity(state);
        ResponseEntity<List<CityDo>> cityResponseEntity = new ResponseEntity<>(cityList, HttpStatus.OK);
        return cityResponseEntity;
    }


    @MethodLogAnnotation(desc = "查找City")
    @RequestMapping(value = "/city/list", method = RequestMethod.GET)
    public ResponseEntity<List<CityDo>> selectAll() {
        List<CityDo> cityList = cityBiz.selectAll();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

}
