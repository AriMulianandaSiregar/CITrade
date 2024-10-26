package com.example.l20231028_finalproject.pojo;

import lombok.Data;

@Data
public class TransactionDTO {
    private int transaction_id;
    private String transactionDate;
    private String paymentStatus;
    private String country;
    private String city;
    private int user_id;
    private int item_id;
    private String name;
    private double price;
    private String itemPic;
}
