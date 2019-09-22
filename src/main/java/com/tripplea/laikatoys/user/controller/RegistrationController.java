package com.tripplea.laikatoys.user.controller;

import com.tripplea.laikatoys.user.model.Role;
import com.tripplea.laikatoys.user.model.User;
import com.tripplea.laikatoys.user.repository.UserRepo;
import com.tripplea.laikatoys.user.service.UserServices;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserServices userServices;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user){
        if (!userServices.addUser(user))
            return "/registration";
        return "redirect:/login";
    }
}
