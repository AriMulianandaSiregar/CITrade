package com.example.l20231028_finalproject.service.impl;

import com.example.l20231028_finalproject.mapper.WalletMapper;
import com.example.l20231028_finalproject.pojo.User;
import com.example.l20231028_finalproject.pojo.Wallet;
import com.example.l20231028_finalproject.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletMapper walletMapper;

    @Override
    public Wallet findWallet(int id) {
        Wallet w = walletMapper.findWallet(id);
        return w;
    }
    @Override
    public void topUp(int wallet_id) {
        walletMapper.topUp(wallet_id);
    }

    @Override
    public void pay(int wallet_id, double price) {
        walletMapper.pay(wallet_id,price);
    }
}
