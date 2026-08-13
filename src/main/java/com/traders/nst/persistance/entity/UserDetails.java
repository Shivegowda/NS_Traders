package com.traders.nst.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name="user_details")
@Data
public class UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String fullName;
    private String password;
    private String mobNo;
    private Timestamp created;
}
