package com.gg.controller;

import com.gg.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/testUser")
    public @ResponseBody User testUser(@RequestBody User user) {
        System.out.println(user.toString());
        user.setName("roobackName");
        user.setAge(999);
        return user;
    }
}
