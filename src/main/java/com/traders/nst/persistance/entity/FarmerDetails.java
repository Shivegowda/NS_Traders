package com.traders.nst.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class FarmerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmerId;
    private String farmerName;
    private String mobileNumber;
    private String address;
    private String status;
    private Timestamp createdDate;
}
