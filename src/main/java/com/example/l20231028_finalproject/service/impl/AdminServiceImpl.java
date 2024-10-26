package com.example.l20231028_finalproject.service.impl;

import com.example.l20231028_finalproject.mapper.AdminMapper;
import com.example.l20231028_finalproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void addCatagory(String catagoryName) {
        adminMapper.addCatagory(catagoryName);
    }
}
