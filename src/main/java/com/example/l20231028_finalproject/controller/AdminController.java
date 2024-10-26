package com.example.l20231028_finalproject.controller;

import com.example.l20231028_finalproject.pojo.Brand;
import com.example.l20231028_finalproject.pojo.Catagory;
import com.example.l20231028_finalproject.pojo.Item;
import com.example.l20231028_finalproject.service.AdminService;
import com.example.l20231028_finalproject.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ItemService itemService;

    @RequestMapping("/adminHome")
    public String adminHome() {
        return "admin/adminHome";
    }

    @RequestMapping("/adminAdd")
    public String adminAdd(Model m) {
        List<Catagory> catagoryList = itemService.allCategoryList();
        List<Brand> brandList = itemService.allBrandList();
        m.addAttribute("categorylist", catagoryList);
        m.addAttribute("brandlist", brandList);
        return "admin/adminAdd";
    }

    @RequestMapping("/adminAddC")
    public String adminAddC() {
        return "admin/adminAddC";
    }

    @RequestMapping("/adminManagement")
    public String adminManagement(Model m) {
        List<Item> itemList = itemService.allItemList();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        return "admin/adminManagement";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index.html";
    }


    @RequestMapping("/addCatagory")
    public String addCatagory(String catagoryName, Model m){
        adminService.addCatagory(catagoryName);
        m.addAttribute("errmsg","add catagory succesfully");
        return "admin/adminAddC";
    }
}









