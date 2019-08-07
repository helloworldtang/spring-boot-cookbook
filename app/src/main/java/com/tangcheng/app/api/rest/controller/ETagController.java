package com.tangcheng.app.api.rest.controller;

import com.tangcheng.app.service.biz.ETagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangcheng on 5/26/2017.
 */
@Api(tags = "ETag Demo", description = "watch HttpStatus")
@RestController
@RequestMapping("/etag")
public class ETagController {

    @Autowired
    private ETagService eTagService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        System.out.println("ETag");
        System.err.println("ETag");
        return ResponseEntity.ok(eTagService.list());
    }


}
