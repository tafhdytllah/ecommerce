package com.tafh.ecommerce.restful.service;


import com.tafh.ecommerce.restful.entity.Customer;
import com.tafh.ecommerce.restful.entity.Product;
import com.tafh.ecommerce.restful.entity.Transaction;
import com.tafh.ecommerce.restful.model.TransactionResponse;
import com.tafh.ecommerce.restful.repository.CustomerRepository;
import com.tafh.ecommerce.restful.repository.ProductRepository;
import com.tafh.ecommerce.restful.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TransactionResponse findByProductAndCustomer(String productId, String customerId) {

        Product product = productRepository.findById(Integer.parseInt(productId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        Customer customer = customerRepository.findById(Integer.parseInt(customerId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not found"));

        Transaction transaction = transactionRepository.findByProductAndCustomer(product, customer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found"));

        return toTransactionResponse(transaction);

    }

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
