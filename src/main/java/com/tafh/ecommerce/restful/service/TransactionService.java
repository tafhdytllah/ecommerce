package com.tafh.ecommerce.restful.service;


import com.tafh.ecommerce.restful.entity.Customer;
import com.tafh.ecommerce.restful.entity.Product;
import com.tafh.ecommerce.restful.entity.Transaction;
import com.tafh.ecommerce.restful.model.CreateTransactionRequest;
import com.tafh.ecommerce.restful.model.TransactionResponse;
import com.tafh.ecommerce.restful.model.UpdateTransactionRequest;
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
import java.time.format.DateTimeFormatter;
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
    public TransactionResponse findFirstByProductAndCustomer(Long productId, Long customerId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not found"));

        Transaction transaction = transactionRepository.findFirstByProductAndCustomer(product, customer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found"));

        return toTransactionResponse(transaction);

    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> findAll() {

        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map(this::toTransactionResponse).toList();
    }

    @Transactional
    public TransactionResponse create(CreateTransactionRequest request) {
        validationService.validate(request);

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not found"));

        Transaction transaction = new Transaction();
        transaction.setProduct(product);
        transaction.setCustomer(customer);
        transaction.setAmount(request.getAmount());
        transaction.setStatus(request.getStatus());
        transaction.setCreateBy(request.getCreateBy());
        transactionRepository.save(transaction);

        return toTransactionResponse(transaction);
    }

    @Transactional
    public TransactionResponse update(UpdateTransactionRequest request, Long productId, Long customerId) {
        validationService.validate(request);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not found"));

        Transaction transaction = transactionRepository.findFirstByProductAndCustomer(product, customer)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found"));

        transaction.setAmount(request.getAmount());
        transaction.setStatus(request.getStatus());
        transaction.setCreateBy(request.getCreateBy());
        transactionRepository.save(transaction);

        return toTransactionResponse(transaction);
    }

    @Transactional
    public void remove(Long productId, Long customerId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not found"));

        Transaction transaction = transactionRepository.findFirstByProductAndCustomer(product, customer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found"));

        transactionRepository.delete(transaction);

    }

    private TransactionResponse toTransactionResponse(Transaction transaction) {


        return TransactionResponse.builder()
                .id(transaction.getId())
                .productID(transaction.getProduct().getId().toString())
                .productName(transaction.getProduct().getName())
                .amount(transaction.getAmount().toString())
                .customerName(transaction.getCustomer().getName())
                .status(transaction.getStatus())
                .transactionDate(dateTimeFormatter(transaction.getTransactionDate()))
                .createBy(transaction.getCreateBy())
                .createOn(dateTimeFormatter(transaction.getCreatedAt()))
                .build();
    }

    private String dateTimeFormatter(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTime.format(formatter);
    }

}
