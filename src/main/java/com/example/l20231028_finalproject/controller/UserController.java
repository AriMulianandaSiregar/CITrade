package com.example.l20231028_finalproject.controller;

import com.example.l20231028_finalproject.pojo.*;
import com.example.l20231028_finalproject.service.ItemService;
import com.example.l20231028_finalproject.service.TransactionService;
import com.example.l20231028_finalproject.service.UserService;
import com.example.l20231028_finalproject.service.WalletService;
import com.example.l20231028_finalproject.utils.Md5Util;
import com.example.l20231028_finalproject.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/login")
    public String login(String username, String password,
                        Model m, HttpServletRequest request) {
        if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
            return "admin/adminHome";
        }
        User loginUser = userService.findUserByUserName(username);
        if (loginUser == null) {
            m.addAttribute("errmsg", "username is wrong.");
            return "user/login";
        }
        if (!Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            m.addAttribute("errmsg", "password is wrong.");
            return "user/login";
        }

        UserVo userVo = new UserVo();
        userVo.setUser_id(loginUser.getUser_id());
        userVo.setUsername(loginUser.getUsername());
        userVo.setPassword(loginUser.getPassword());
        userVo.setWallet_id(loginUser.getWallet_id());
        request.getSession().setAttribute("currentuser",userVo);
        m.addAttribute("userinformation",loginUser);

        List<Item> itemList = itemService.allItemListUser();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        return "user/home";
    }

    @RequestMapping("/register")
    public String registerUser(String username, String password, String phoneNumber, String email, String address, Model m) {
        User user = userService.findUserByUserName(username);
        if (user == null) {
            userService.registerUser(username, password, phoneNumber, email, address);
            return "index.html";
        } else {
            m.addAttribute("errmsg","username is used");
            return "register.html";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index.html";
    }

    @RequestMapping("/account")
    public String account(Model m) {
        UserVo userVo = ThreadLocalUtil.get();
        User user = userService.findUser(userVo.getUsername(),userVo.getPassword());
        Wallet wallet = walletService.findWallet(userVo.getWallet_id());
        m.addAttribute("userinfo",user);
        m.addAttribute("walletinfo",wallet);
        return "user/account";
    }

    @RequestMapping("/history")
    public String history(HttpServletRequest request, Model m) {
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);

        List<TransactionDTO> transactionDTOS = transactionService.getAllPaidTransactions(currentUser.getUser_id());
        m.addAttribute("paidTransactionList",transactionDTOS);

        return "user/history";
    }

    @RequestMapping("/cart")
    public String cart(HttpServletRequest request, Model m) {
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);

        List<TransactionDTO> transactionDTOS =  transactionService.getAllUnpaidTransactions(currentUser.getUser_id());
        m.addAttribute("unpaidTransactionList",transactionDTOS);

        return "user/cart";
    }

    @RequestMapping("/home")
    public String home(Model m) {

        UserVo userVo = ThreadLocalUtil.get();
        User user = userService.findUser(userVo.getUsername(),userVo.getPassword());
        m.addAttribute("userinformation",user);

        List<Item> itemList = itemService.allItemListUser();
        Brand brand = null;
        for(Item item : itemList){
            brand = itemService.findBrandById(item.getBrand_id());
            item.setBrandName(brand.getBrandName());
        }
        m.addAttribute("itemList",itemList);
        return "user/home";
    }

    @PostMapping("/topUp")
    public String topUp(@RequestParam("wallet_id") int wallet_id, Model m){
        walletService.topUp(wallet_id);
        UserVo userVo = ThreadLocalUtil.get();
        User user = userService.findUser(userVo.getUsername(),userVo.getPassword());
        Wallet wallet = walletService.findWallet(userVo.getWallet_id());
        m.addAttribute("userinfo",user);
        m.addAttribute("walletinfo",wallet);
        return "user/account";
    }
}
