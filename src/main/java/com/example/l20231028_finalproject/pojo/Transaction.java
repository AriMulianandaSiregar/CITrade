package com.example.l20231028_finalproject.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Transaction {
    private int transaction_id;
    private LocalDateTime transactionDate;
    private String paymentStatus;
    private int customer_id;
    private int location_id;
    private int item_id;
}
