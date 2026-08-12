package com.traders.nst.DTO.Response;

import lombok.Data;

@Data
public class LoginResponse {
    private String userName;
    private String jwtToken;
}
