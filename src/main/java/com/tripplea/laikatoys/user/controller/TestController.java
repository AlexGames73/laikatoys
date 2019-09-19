package com.tripplea.laikatoys.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TestController {
    @GetMapping("/")
    public String hello(Map<String, Object> model) {
        return "hello";
    }
    @GetMapping("/home")
    public String home(Map<String, Object> model){
        return "home";
    }
}
