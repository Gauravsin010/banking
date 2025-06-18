package com.example.banking.service;

import com.example.banking.entity.Users;

public interface UserService {

    public Users registerUser(Users users);

    public String login(Users users);
}
