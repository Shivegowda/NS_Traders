package com.traders.nst.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {
    private Long productId;
    private String productName;
    private Double productRate;
    private Double netQuantity;
}
