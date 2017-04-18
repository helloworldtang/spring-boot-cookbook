package com.tangcheng.rest;

import com.tangcheng.api.UserService;
import com.tangcheng.global.domain.ResultData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tang.cheng on 2017/4/18.
 */
@Api(tags = "user")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResultData<?> addUser(@RequestParam String username,
                                 @RequestParam String email,
                                 @RequestParam String password) {
        return userService.addUser(username, email,password);
    }

    @GetMapping("{username}")
    public ResultData<?> getUser(@PathVariable String username) {
        return userService.getUser(username);
    }


    @PutMapping("{username}")
    public ResultData<?> modifyUser(@PathVariable String username,
                                    @RequestParam(value = "email") String email) {
        return userService.putUser(username, email);
    }

    @DeleteMapping("{username}")
    public ResultData<?> delUser(@PathVariable String username) {
        return userService.delUser(username);
    }

}
