package com.tangcheng.app.authorization.restful;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by tangcheng on 5/15/2017.
 */
@RestController
public class DefaultController {

    @GetMapping("user")
    public Principal principal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
