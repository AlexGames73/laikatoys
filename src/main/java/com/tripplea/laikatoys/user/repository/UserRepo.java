package com.tripplea.laikatoys.user.repository;

import com.tripplea.laikatoys.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(int id);
    User findByEmail(String email);

    User findByActivationCode(String code);
}
