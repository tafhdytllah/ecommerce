package com.tafh.ecommerce.restful.service;


import com.tafh.ecommerce.restful.entity.Transaction;
import com.tafh.ecommerce.restful.model.TransactionResponse;
import com.tafh.ecommerce.restful.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public List<TransactionResponse> findAll() {

        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map(this::toTransactionResponse).toList();
    }

    private TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .productID(transaction.getProduct().getId().toString())
                .productName(transaction.getProduct().getName())
                .amount(transaction.getAmount().toString())
                .customerName(transaction.getCustomer().getName())
                .status(transaction.getStatus())
                .transactionDate(toLocalDateTime(transaction.getTransactionDate()))
                .createBy(transaction.getCreateBy())
                .createOn(toLocalDateTime(transaction.getCreatedAt()))
                .build();
    }

    private String toLocalDateTime(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
