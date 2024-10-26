package com.example.l20231028_finalproject.service.impl;

import com.example.l20231028_finalproject.mapper.UserMapper;
import com.example.l20231028_finalproject.mapper.WalletMapper;
import com.example.l20231028_finalproject.pojo.User;
import com.example.l20231028_finalproject.pojo.UserVo;
import com.example.l20231028_finalproject.pojo.Wallet;
import com.example.l20231028_finalproject.service.UserService;
import com.example.l20231028_finalproject.utils.Md5Util;
import com.example.l20231028_finalproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WalletMapper walletMapper;

    @Override
    public User findUser(String username, String password) {
        User u = userMapper.findUser(username,password);
        return u;
    }
    @Override
    public User findUserByUserName(String username) {
        User u = userMapper.findUserByUserName(username);
        return u;
    }
    @Override
    public void registerUser(String username, String password, String phoneNumber, String email, String address) {
        String md5String = Md5Util.getMD5String(password);
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(8);
        Wallet wallet = null;
        int randomWallet_id;
        do {
            for (int i = 0; i < 8; i++) {
                // Pilih karakter acak dari 'characters'
                int index = random.nextInt(characters.length());
                char randomChar = characters.charAt(index);
                stringBuilder.append(randomChar);
            }
            randomWallet_id = Integer.parseInt(stringBuilder.toString());
            wallet = walletMapper.findWallet(randomWallet_id);
        } while (wallet != null);
        walletMapper.registerWallet(randomWallet_id);
        userMapper.registerUser(username, md5String, phoneNumber, email, address, randomWallet_id);
    }
}
