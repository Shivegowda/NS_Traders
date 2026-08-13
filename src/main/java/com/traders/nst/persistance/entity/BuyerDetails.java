package com.traders.nst.persistance.entity;

import com.traders.nst.enums.ActivationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class BuyerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BuyerId;
    private String BuyerName;
    private String mobileNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private ActivationStatus status;
    private Timestamp createdDate;
}

