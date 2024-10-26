package com.example.l20231028_finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class APPController {
    @RequestMapping("/")
    public String toIndex(){
        return "index.html";
    }

    @RequestMapping("/tologin")
    public String toLogin(){
        return "index.html";
    }

    @RequestMapping("/toregister")
    public String toReg(){
        return "register.html";
    }

    @RequestMapping("/toAdminHome")
    public String toAdminHome(){
        return "admin/adminHome";
    }
}
