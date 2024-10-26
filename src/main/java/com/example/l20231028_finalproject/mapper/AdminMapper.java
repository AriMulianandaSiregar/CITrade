package com.example.l20231028_finalproject.mapper;

import com.example.l20231028_finalproject.pojo.Item;
import com.example.l20231028_finalproject.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    @Insert("insert into Catagory(catagoryName) " +
            "values(#{catagoryName})")
    void addCatagory(String catagoryName);
}
