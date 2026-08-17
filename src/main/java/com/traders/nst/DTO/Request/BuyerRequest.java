package com.traders.nst.DTO.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.traders.nst.enums.ActivationStatus;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyerRequest {
    private Long BuyerId;
    private String BuyerName;
    private String mobileNumber;
    private String address;
    private ActivationStatus status;
}
