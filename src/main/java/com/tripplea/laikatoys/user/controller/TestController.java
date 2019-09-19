package com.tripplea.laikatoys.user.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            model.put("nameUser", currentUserName);
        }
        return "home";
    }
}
