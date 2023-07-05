package com.springbootdemo.springbootdemo.service;

import com.springbootdemo.springbootdemo.model.User;
import com.springbootdemo.springbootdemo.repository.InvoiceRepository;
import com.springbootdemo.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser() {
        String id = UUID.randomUUID().toString();
        // always "finds" the user, every user has a random name
        User user =  new User(id, "User => " + id);
        return this.userRepository.save(user);
    }

    public User findById(String id) {
        return this.userRepository.findByUserId(id).iterator().next();
    }
}