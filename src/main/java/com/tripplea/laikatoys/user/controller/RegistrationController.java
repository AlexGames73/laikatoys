package com.tripplea.laikatoys.user.controller;

import com.tripplea.laikatoys.user.model.Role;
import com.tripplea.laikatoys.user.model.User;
import com.tripplea.laikatoys.user.repository.UserRepo;
import com.tripplea.laikatoys.user.service.UserServices;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/login")
    public String isLogin(@AuthenticationPrincipal User authUser, Model model){
        if (authUser == null)
            return "login";
        model.addAttribute("user", authUser);
        return "user/home";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userServices.activateUser(code);
        if (isActivated){
            model.addAttribute("message", "User successfully activate");
        }
        else {
            model.addAttribute("message", "Activation code is not found");
        }
        return "login";
    }
}
