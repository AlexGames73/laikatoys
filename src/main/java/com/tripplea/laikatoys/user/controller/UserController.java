package com.tripplea.laikatoys.user.controller;

import com.tripplea.laikatoys.user.model.Role;
import com.tripplea.laikatoys.user.model.User;
import com.tripplea.laikatoys.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

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
        model.put("user", authUser);
        return "user/home";
    }

    @GetMapping("{userId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String userEditShow(@PathVariable Integer userId, Model model, @AuthenticationPrincipal User authUser){
        if (authUser.getId().equals(userId) || authUser.isAdmin()) {
            User user = userRepo.findById(userId);
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
            model.addAttribute("isError", false);
        } else {
            model.addAttribute("error", "Access denied");
            model.addAttribute("isError", true);
        }
        return "user/settingUser";
    }

    @PostMapping(value = "/settingUser")
    public String userEditChange(@ModelAttribute User user, @RequestParam Map<String, String> form, Model model, @AuthenticationPrincipal User authUser){
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()){
            if (roles.contains(key))
                user.getRoles().add(Role.valueOf(key));
        }
        userRepo.save(user);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("authUser", authUser);
        return "user/settingUser";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showUsers(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute(users);
        return "user/users";
    }

    @GetMapping("/setting")
    public String mySetting(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("authUser",user);
        model.addAttribute("roles", Role.values());
        return "/user/settingUser";
    }

}
