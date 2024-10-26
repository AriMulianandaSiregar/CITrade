package com.example.l20231028_finalproject.mapper;

import com.example.l20231028_finalproject.pojo.User;
import com.example.l20231028_finalproject.pojo.Wallet;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from customer where username=#{username} AND password =#{password}")
    User findUser(String username,String password);

    @Select("SELECT * FROM customer WHERE username = #{username}")
    @Results(id = "CustomerMap", value = {
            @Result(property = "user_id", column = "customer_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNumber", column = "phoneNumber"),
            @Result(property = "email", column = "email"),
            @Result(property = "address", column = "address"),
            @Result(property = "wallet_id", column = "wallet_id")
    })
    User findUserByUserName(@Param("username") String username);

    @Insert("insert into customer(username,password, phoneNumber, email, address, wallet_id)" +
            " values(#{username},#{password},#{phoneNumber},#{email},#{address},#{wallet_id})")
    void registerUser(String username, String password, String phoneNumber, String email, String address, int wallet_id);


}
