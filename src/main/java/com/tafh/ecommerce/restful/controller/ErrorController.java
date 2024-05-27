package com.tafh.ecommerce.restful.controller;

import com.tafh.ecommerce.restful.model.StatusResponse;
import com.tafh.ecommerce.restful.model.WebResponse;
import com.tafh.ecommerce.restful.util.StatusHelper;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class ErrorController {

    @Autowired
    private StatusHelper statusHelper;
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<List<StatusResponse>>> constraintViolationException(ConstraintViolationException exception) {
        List<StatusResponse> failedStatus = statusHelper.getStatusResponse(1);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<List<StatusResponse>>builder().status(failedStatus).errors(exception.getMessage()).build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<List<StatusResponse>>> apiException(ResponseStatusException exception) {
        List<StatusResponse> failedStatus = statusHelper.getStatusResponse(1);

        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponse.<List<StatusResponse>>builder().status(failedStatus).errors(exception.getReason()).build());
    }
}
