package com.example.l20231028_finalproject.service.impl;

import com.example.l20231028_finalproject.mapper.TransactionMapper;
import com.example.l20231028_finalproject.pojo.Item;
import com.example.l20231028_finalproject.pojo.Transaction;
import com.example.l20231028_finalproject.pojo.TransactionDTO;
import com.example.l20231028_finalproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public void addToCart(Transaction transaction) {
        transactionMapper.addToCart(transaction);
    }

    @Override
    public List<TransactionDTO> getAllUnpaidTransactions(int user_id) {
        List<TransactionDTO> transactionDTOS = transactionMapper.getAllUnpaidTransactions(user_id);
        return transactionDTOS;
    }

    @Override
    public List<TransactionDTO> getAllPaidTransactions(int user_id) {
        List<TransactionDTO> transactionDTOS = transactionMapper.getAllpaidTransactions(user_id);
        return transactionDTOS;
    }

    @Override
    public TransactionDTO paynow(int transaction_id) {
        TransactionDTO transactionDTO = transactionMapper.paynow(transaction_id);
        return transactionDTO;
    }

    @Override
    public void checkout(int transaction_id) {
        transactionMapper.checkout(transaction_id);
    }

    @Override
    public TransactionDTO detailHistory(int transaction_id) {
        TransactionDTO transactionDTO = transactionMapper.detailHistory(transaction_id);
        return transactionDTO;
    }

    @Override
    public void deleteTransactionById(int transaction_id) {
        transactionMapper.deleteTransactionById(transaction_id);
    }


}
