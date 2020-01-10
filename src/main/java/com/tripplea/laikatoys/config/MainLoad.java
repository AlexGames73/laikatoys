package com.tripplea.laikatoys.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainLoad {
    @GetMapping("/")
    public String ds(){
        return "main";
    }

    @GetMapping("/about")
    public String aboutLoad(){
        return "about";
    }
}
