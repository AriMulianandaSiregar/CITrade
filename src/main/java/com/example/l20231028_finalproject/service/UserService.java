package com.example.l20231028_finalproject.service;

import com.example.l20231028_finalproject.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User findUser(String username,String password);
    User findUserByUserName(String username);
    void registerUser(String username, String password, String phoneNumber, String email, String address);
}
