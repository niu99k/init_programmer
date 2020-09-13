package com.gg.controller;

import com.gg.domain.Account;
import com.gg.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @RequestMapping("/testAccount")
    public String testAccount(User user) {
        System.out.println(user.toString());
        return "success";
    }
}
