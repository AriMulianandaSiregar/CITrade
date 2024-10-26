package com.example.l20231028_finalproject.controller;

import com.example.l20231028_finalproject.pojo.*;
import com.example.l20231028_finalproject.service.ItemService;
import com.example.l20231028_finalproject.service.TransactionService;
import com.example.l20231028_finalproject.service.WalletService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequestMapping("/transaction")
@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private WalletService walletService;


    @RequestMapping("/addToCart")
    public String addToCart(@RequestParam("itemId") int item_id, HttpServletRequest request, Model m) throws IOException {
        itemService.purchased(item_id);
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPaymentStatus("unpaid");
        transaction.setCustomer_id(currentUser.getUser_id());
        transaction.setLocation_id(1);
        transaction.setItem_id(item_id);
        transactionService.addToCart(transaction);

        List<TransactionDTO> transactionDTOS =  transactionService.getAllUnpaidTransactions(currentUser.getUser_id());
        m.addAttribute("unpaidTransactionList",transactionDTOS);

        return "user/cart";
    }

    @RequestMapping("/payNow")
    public String payNow(@RequestParam("transactionId") int transaction_id, HttpServletRequest request, Model m) throws IOException{
        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);

        List<TransactionDTO> transactionDTOS =  transactionService.getAllUnpaidTransactions(currentUser.getUser_id());
        m.addAttribute("unpaidTransactionList",transactionDTOS);

        TransactionDTO transactionDTO = transactionService.paynow(transaction_id);
        m.addAttribute("transactionInformation",transactionDTO);
        System.out.println("pay now");
        return "user/cart";
    }

    @RequestMapping("/checkout")
    public String checkout(@RequestParam("transactionId") int transaction_id, HttpServletRequest request, Model m) throws IOException{

        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);

        Wallet wallet = walletService.findWallet(currentUser.getWallet_id());
        TransactionDTO transactionDTO = transactionService.paynow(transaction_id);

        if(wallet.getBalance() < transactionDTO.getPrice()){
            List<TransactionDTO> transactionDTOs = transactionService.getAllUnpaidTransactions(currentUser.getUser_id());
            m.addAttribute("unpaidTransactionList",transactionDTOs);
            m.addAttribute("errmsg","you dont have enough balance, please go to account and do top up");
            return "user/cart";
        }else{
            walletService.pay(currentUser.getWallet_id(),transactionDTO.getPrice());
            transactionService.checkout(transaction_id);
            List<TransactionDTO> transactionDTOs = transactionService.getAllPaidTransactions(currentUser.getUser_id());
            m.addAttribute("paidTransactionList",transactionDTOs);
            return "user/history";
        }



    }

    @RequestMapping("/detailHistory")
    public String detailHistory(@RequestParam("transactionId") int transaction_id, HttpServletRequest request, Model m) throws IOException{
        TransactionDTO transactionDTO = transactionService.detailHistory(transaction_id);
        m.addAttribute("transactionInformation",transactionDTO);

        Item item = itemService.findItemById(transactionDTO.getItem_id());
        m.addAttribute("itemInformation",item);

        Catagory catagory = itemService.findCatagoryById(item.getCatagory_id());
        m.addAttribute("catagoryInformation", catagory);

        Brand brand = itemService.findBrandById(item.getBrand_id());
        m.addAttribute("brandInformation",brand);

        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);

        List<TransactionDTO> transactionDTOs = transactionService.getAllPaidTransactions(currentUser.getUser_id());
        m.addAttribute("paidTransactionList",transactionDTOs);
        return "user/history";
    }



    @PostMapping("/deleteHistory")
    public String deleteHistory(@RequestParam("deletePaidId") int paid_id, HttpServletRequest request ,Model m) {
        System.out.println("transaction id : " + paid_id);
        transactionService.deleteTransactionById(paid_id);

        UserVo currentUser = (UserVo) request.getSession().getAttribute("currentuser");
        m.addAttribute("userinformation",currentUser);

        List<TransactionDTO> transactionDTOs = transactionService.getAllPaidTransactions(currentUser.getUser_id());
        m.addAttribute("paidTransactionList",transactionDTOs);
        return "user/history";
    }
}
