package com.gg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SuccessController {
    @RequestMapping("/testAccount")
    public String testSuc() {
        System.out.println("test succress");
        return "success";
    }
}
