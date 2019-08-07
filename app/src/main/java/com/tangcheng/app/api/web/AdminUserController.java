package com.tangcheng.app.api.web;

import com.tangcheng.app.domain.query.UserQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addUser(@Validated UserQuery userQuery, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            redirect.addAttribute("user", userQuery);
            redirect.addAttribute("formErrors", result.getAllErrors());
            redirect.addAttribute("url", "/admin/user");
            return "user/register";
        }
        redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
        return "redirect:/";
    }


}
