package com.tafh.ecommerce.restful.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTransactionRequest {

    private Integer amount;

    private Boolean status;

    @JsonProperty("create_by")
    private String createBy;
}
