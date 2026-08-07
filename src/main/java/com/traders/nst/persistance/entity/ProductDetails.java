package com.traders.nst.persistance.entity;

import com.traders.nst.enums.ActivationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productRate;
    @Enumerated(EnumType.STRING)
    private ActivationStatus productStatus;
    private Timestamp rateChangeDate;
}
