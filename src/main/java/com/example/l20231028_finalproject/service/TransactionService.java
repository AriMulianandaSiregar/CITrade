package com.example.l20231028_finalproject.service;

import com.example.l20231028_finalproject.pojo.Item;
import com.example.l20231028_finalproject.pojo.Transaction;
import com.example.l20231028_finalproject.pojo.TransactionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    void addToCart(Transaction transaction);

    List<TransactionDTO> getAllUnpaidTransactions(int user_id);

    List<TransactionDTO> getAllPaidTransactions(int user_id);

    TransactionDTO paynow(int transaction_id);

    void checkout(int transaction_id);

    TransactionDTO detailHistory(int transaction_id);

    void deleteTransactionById(int transaction_id);
}
