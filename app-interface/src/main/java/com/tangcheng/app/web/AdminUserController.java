package com.tangcheng.app.web;

import com.tangcheng.app.domain.query.UserQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tangcheng on 6/28/2017.
 */
@Controller
@RequestMapping("admin/user")
public class AdminUserController {

    @GetMapping
    public String addUser(Model model) {
        model.addAttribute("user", new UserQuery());
        model.addAttribute("url", "/admin/user");
        return "user/register";
    }

    @PostMapping
    public String addUser(@Validated UserQuery userQuery, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", userQuery);
            model.addAttribute("url", "/admin/user");
            return "user/register";
        }
        return "redirect:/";
    }


}
