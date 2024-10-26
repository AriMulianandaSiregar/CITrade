package com.example.l20231028_finalproject.mapper;

import com.example.l20231028_finalproject.pojo.Wallet;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WalletMapper {
    @Insert("insert into wallet (wallet_id, balance)" +
            " values(#{randomWallet_id},0)")
    void registerWallet(int randomWallet_id);

    @Select("SELECT * FROM wallet WHERE wallet_id=#{Wallet_id}")
    @Results(id = "WalletMap", value = {
            @Result(property = "wallet_id", column = "wallet_id"),
            @Result(property = "balance", column = "balance")
    })
    Wallet findWallet(int Wallet_id);

    @Update("update wallet set balance = balance + 100 WHERE wallet_id=#{wallet_id}")
    void topUp(int wallet_id);

    @Update("update wallet set balance = balance - #{price} WHERE wallet_id=#{wallet_id}")
    void pay(int wallet_id, double price);
}
