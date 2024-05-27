package com.tafh.ecommerce.restful.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    private Long id;

    private String productID;

    private String productName;

    private String amount;

    private String customerName;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Boolean status;

    private String transactionDate;

    private String createBy;

    private String createOn;

}
