package com.tangcheng.app.resource.restful;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by tangcheng on 5/15/2017.
 */
@RestController
public class DefaultController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("success");
    }

    @GetMapping("user")
    public Principal principal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


}
