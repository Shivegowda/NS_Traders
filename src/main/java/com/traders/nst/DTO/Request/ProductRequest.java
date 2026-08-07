package com.traders.nst.DTO.Request;

import com.traders.nst.enums.ActivationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Invalid field value")
    private Double productRate;
    private Long  productId;
    private ActivationStatus productStatus;
}
