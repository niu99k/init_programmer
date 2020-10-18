package com.gg.exception.controller;

import com.gg.exception.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
    @RequestMapping("/hello")
    public String hello() throws Exception{
        throw new Exception("test error");
    }
    @RequestMapping("/hello2")
    public String hello2() throws MyException {
        throw new MyException("test error2");
    }
}
