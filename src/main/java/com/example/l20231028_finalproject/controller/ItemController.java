package com.example.l20231028_finalproject.controller;

import com.example.l20231028_finalproject.pojo.Brand;
import com.example.l20231028_finalproject.pojo.Catagory;
import com.example.l20231028_finalproject.pojo.Item;
import com.example.l20231028_finalproject.pojo.UserVo;
import com.example.l20231028_finalproject.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/item")
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/addItem")
    public String addItem(Item item, MultipartFile cover, Model m) throws IOException {
        UUID uuid = UUID.randomUUID();
        String filename = uuid + ".png";
        cover.transferTo(new File(filename));
        String itemPic = "/img/" + filename;
        item.setItemPic(itemPic);
        itemService.addItem(item);
        List<Catagory> catagoryList = itemService.allCategoryList();
        List<Brand> brandList = itemService.allBrandList();
        m.addAttribute("categorylist", catagoryList);
        m.addAttribute("brandlist", brandList);
        return "admin/adminAdd";
    }

    @PostMapping("/deleteItem")
    public String deleteItem(@RequestParam("itemId") int item_id, Model m) {
        System.out.println("Masukkkkk deleteeeeee");
        itemService.deleteItemById(item_id);
        List<Item> itemList = itemService.allItemList();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        return "admin/adminManagement";
    }

    @PostMapping("/editItem")
    public String editItem(@RequestParam("itemId") int item_id,
                           @RequestParam("editItemName") String editItemName,
                           @RequestParam("editItemStock") int editItemStock,
                           @RequestParam("editItemPrice") double editItemPrice,
                           Model m){
        itemService.editItemById(item_id,editItemName,editItemStock,editItemPrice);
        List<Item> itemList = itemService.allItemList();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        return "admin/adminManagement";
    }

    @PostMapping("/searchItem")
    public String searchItem(String itemSearch, Model m, HttpServletRequest request){
        List<Item> itemList = itemService.searchItemByName(itemSearch);
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);
        return "user/home";
    }

    @GetMapping("/allCatagory")
    public String allCatagory(Model m, HttpServletRequest request){
        List<Item> itemList = itemService.allItemList();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);
        return "user/home";
    }

    @GetMapping("/foodCatagory")
    public String foodCatagory(Model m, HttpServletRequest request){
        List<Item> itemList = itemService.allFoodItemList();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);
        return "user/home";
    }

    @GetMapping("/fashionCatagory")
    public String fashionCatagory(Model m, HttpServletRequest request){
        List<Item> itemList = itemService.allFashionItemList();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);
        return "user/home";
    }

    @GetMapping("/electronicCatagory")
    public String electronicCatagory(Model m, HttpServletRequest request){
        List<Item> itemList = itemService.allElectronicItemList();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);
        return "user/home";
    }

    @GetMapping("/souvenirCatagory")
    public String souvenirCatagory(Model m, HttpServletRequest request){
        List<Item> itemList = itemService.allSouvenirItemList();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);
        return "user/home";
    }

    @GetMapping("/mediaCatagory")
    public String mediaCatagory(Model m, HttpServletRequest request){
        List<Item> itemList = itemService.allMediaItemList();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);
        return "user/home";
    }
}

// @RequestMapping is combination between @PostMapping and @GetMapping