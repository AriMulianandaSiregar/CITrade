package com.example.l20231028_finalproject.service;

import com.example.l20231028_finalproject.pojo.User;
import com.example.l20231028_finalproject.pojo.Wallet;

public interface WalletService {
    Wallet findWallet(int id);
    void topUp(int wallet_id);
    void pay(int wallet_id, double price);
}
