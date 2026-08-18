package com.traders.nst.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class InventoryDetails {
    @Id
    private Long productId;
    private String productName;
    private Double purchasedQuantity;
    private Double soldQuantity;
    private Double netQuantity;

}
