package com.example.banking.controller;

import com.example.banking.entity.Users;
import com.example.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users users){
        return userService.registerUser(users);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users users){
        return userService.login(users);
    }
}
