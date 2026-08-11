package com.traders.nst.persistance.entity;

import com.traders.nst.enums.OrderType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class PurchaseOrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long productId;
    private String productName;
    private Double rate;
    private Double quantity;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private Long orderedBy;
    private String orderedByName;
    private Timestamp createdDate;
}
