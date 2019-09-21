package com.tripplea.laikatoys.user.controller;

import com.tripplea.laikatoys.user.model.Role;
import com.tripplea.laikatoys.user.model.User;
import com.tripplea.laikatoys.user.repository.UserRepo;
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

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null){
            model.put("message", "User exists");
            return "/registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
