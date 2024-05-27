package com.tafh.ecommerce.restful.controller;

import com.tafh.ecommerce.restful.model.StatusResponse;
import com.tafh.ecommerce.restful.model.TransactionResponse;
import com.tafh.ecommerce.restful.model.WebResponse;
import com.tafh.ecommerce.restful.service.TransactionService;
import com.tafh.ecommerce.restful.util.StatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StatusHelper statusHelper;

    @GetMapping(
        path = "/api/transactions",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<TransactionResponse>> findAll() {
        List<TransactionResponse> transactionResponses = transactionService.findAll();

        List<StatusResponse> statusResponses = statusHelper.getStatusResponse(0);

        return WebResponse.<List<TransactionResponse>>builder().
                data(transactionResponses).status(statusResponses).build();
    }

}
