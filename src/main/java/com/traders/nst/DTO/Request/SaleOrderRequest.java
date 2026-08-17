package com.traders.nst.DTO.Request;

import lombok.Data;

@Data
public class SaleOrderRequest {
    private Long orderId;
    private Long productId;
    private String productName;
    private Double rate;
    private Double quantity;
    private Double amount;
    private Long orderedBy;
    private String orderedByName;
}
