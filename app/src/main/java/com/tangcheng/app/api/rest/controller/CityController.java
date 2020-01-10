package com.tangcheng.app.api.rest.controller;

import com.tangcheng.app.core.aop.MethodLogAnnotation;
import com.tangcheng.app.domain.entity.CityDO;
import com.tangcheng.app.service.biz.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by tang.cheng on 2016/12/1.
 */
@RestController
public class CityController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @MethodLogAnnotation(desc = "查找City")
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public ResponseEntity<List<CityDO>> getCity(@RequestParam("state") String state, HttpServletRequest request) {
        LOGGER.info("{},{},{}", request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort());
        return ResponseEntity.ok(cityService.getCity(state));
    }


    @MethodLogAnnotation(desc = "查找City")
    @RequestMapping(value = "/city/list", method = RequestMethod.GET)
    public ResponseEntity<List<CityDO>> listAll() {
        return ResponseEntity.ok(cityService.listAll());
    }

}
