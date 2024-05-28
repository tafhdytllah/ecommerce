package com.tafh.ecommerce.restful.controller;

import com.tafh.ecommerce.restful.model.*;
import com.tafh.ecommerce.restful.service.TransactionService;
import com.tafh.ecommerce.restful.util.StatusHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StatusHelper statusHelper;

    @GetMapping
    public WebResponse<List<TransactionResponse>> getAllTransactions() {
        List<TransactionResponse> transactionResponses = transactionService.findAll();

        List<StatusResponse> statusResponses = statusHelper.getStatusResponse(0);

        return WebResponse.<List<TransactionResponse>>builder().
                data(transactionResponses).status(statusResponses).build();
    }

    @GetMapping("/products/{product_id}/customers/{customer_id}")
    public WebResponse<TransactionResponse> getTransactionByProductAndCustomer(
            @PathVariable("product_id") String productId,
            @PathVariable("customer_id") String customerId
    ) {

        TransactionResponse transactionResponse = transactionService.findFirstByProductAndCustomer(Long.parseLong(productId), Long.parseLong(customerId));

        List<StatusResponse> successStatus = statusHelper.getStatusResponse(0);

        return WebResponse.<TransactionResponse>builder()
                .data(transactionResponse).status(successStatus).build();
    }

    @PostMapping
    public WebResponse<TransactionResponse> createTransaction(@RequestBody CreateTransactionRequest request) {

        TransactionResponse transactionResponse = transactionService.create(request);

        List<StatusResponse> successStatus = statusHelper.getStatusResponse(0);

        return WebResponse.<TransactionResponse>builder()
                .data(transactionResponse).status(successStatus).build();
    }

    @PutMapping("/products/{product_id}/customers/{customer_id}")
    public WebResponse<TransactionResponse> updateTransaction(
            @RequestBody UpdateTransactionRequest request,
            @PathVariable("product_id") String productId,
            @PathVariable("customer_id") String customerId
    ) {
        TransactionResponse transactionResponse = transactionService.update(request, Long.parseLong(productId), Long.parseLong(customerId));

        List<StatusResponse> successStatus = statusHelper.getStatusResponse(0);

        return WebResponse.<TransactionResponse>builder()
                .data(transactionResponse).status(successStatus).build();
    }

    @DeleteMapping("/products/{product_id}/customers/{customer_id}")
    public WebResponse<String> remove(
            @PathVariable("product_id") String productId,
            @PathVariable("customer_id") String customerId)
    {
        transactionService.remove(Long.parseLong(productId), Long.parseLong(customerId));

        List<StatusResponse> successStatus = statusHelper.getStatusResponse(0);

        return WebResponse.<String>builder().data("OK").status(successStatus).build();
    }

}
