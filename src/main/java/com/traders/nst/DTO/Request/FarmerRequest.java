package com.traders.nst.DTO.Request;

import com.traders.nst.enums.ActivationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerRequest implements Serializable {
    private Long farmerId;
    private ActivationStatus status;
    @NotBlank(message = "Invalid field value")
    private String farmerName;
    @NotBlank(message = "Invalid field value")
    private String mobileNumber;
    @NotBlank(message = "Invalid field value")
    private String address;
}
