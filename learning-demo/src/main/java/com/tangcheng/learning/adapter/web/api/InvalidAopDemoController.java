package com.tangcheng.learning.adapter.web.api;

import com.tangcheng.learning.service.aop.BServiceWithAop;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 10/5/2018 1:01 PM
 */
@RestController
@RequestMapping("aop")
public class InvalidAopDemoController {

    @Autowired
    private BServiceWithAop bServiceWithAop;

    @GetMapping("a/service/invalid/ut")
    public ResponseEntity<String> doIt() {
        bServiceWithAop.doBiz();
        return ResponseEntity.ok("success");
    }


}
