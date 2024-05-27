package com.tafh.ecommerce.restful.controller;

import com.tafh.ecommerce.restful.model.StatusResponse;
import com.tafh.ecommerce.restful.model.TransactionResponse;
import com.tafh.ecommerce.restful.model.WebResponse;
import com.tafh.ecommerce.restful.service.TransactionService;
import com.tafh.ecommerce.restful.util.StatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
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

        TransactionResponse transactionResponse = transactionService.findByProductAndCustomer(productId, customerId);

        List<StatusResponse> successStatus = statusHelper.getStatusResponse(0);

        return WebResponse.<TransactionResponse>builder()
                .data(transactionResponse).status(successStatus).build();
    }

}
