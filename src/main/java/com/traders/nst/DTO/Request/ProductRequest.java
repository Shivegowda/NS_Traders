package com.traders.nst.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Invalid field value")
    private String productName;
    @NotBlank(message = "Invalid field value")
    private String productDescription;
    @NotBlank(message = "Invalid field value")
    private Double productRate;
    private Long  productId;
}
