package com.tripplea.laikatoys.user.service;

import com.tripplea.laikatoys.Mail.service.MailServices;
import com.tripplea.laikatoys.user.model.Role;
import com.tripplea.laikatoys.user.model.User;
import com.tripplea.laikatoys.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServices implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailServices mailServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user){
        if (StringUtils.isEmpty(user.getEmail()))
            return false;
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null && userRepo.findByEmail(user.getEmail()) != null){
            return false;
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        String message = String.format(
                "Hello, %s! \n" +
                        "Please, click on this link: http://localhost:8080/activate/%s",
                user.getUsername(),
                user.getActivationCode()
        );

        mailServices.send(user.getEmail(), "Activation code from Laika Toys", message);
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null)
            return false;
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }

    public void save(User user) {
        userRepo.save(user);
    }
}
