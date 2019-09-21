package com.tripplea.laikatoys.user.controller;

import com.tripplea.laikatoys.user.model.User;
import com.tripplea.laikatoys.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping()
    public String hello(Map<String, Object> model) {
        return "redirect:user/home";
    }
    @GetMapping("/home")
    public String home(Map<String, Object> model, @AuthenticationPrincipal User authUser) {
        model.put("nameUser", authUser.getUsername());
        return "home";
    }

    @GetMapping("{userId}")
    public String userEditShow(@PathVariable Long userId, Model model){
        User user = userRepo.findById(userId.intValue());
        model.addAttribute("user", user);
        return "user/settingUser";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute(users);
        return "user/users";
    }
}
