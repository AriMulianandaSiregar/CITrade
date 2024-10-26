package com.example.l20231028_finalproject.pojo;

import lombok.Data;

@Data
public class User {
    private int user_id;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
    private int wallet_id;
}
