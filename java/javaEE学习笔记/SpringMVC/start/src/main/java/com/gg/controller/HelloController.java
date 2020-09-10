package com.gg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping(path = "/hello")
    public String helloController() {
        System.out.println("suc");
        return "suc";
    }
}
