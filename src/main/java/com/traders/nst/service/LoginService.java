package com.traders.nst.service;

import com.traders.nst.DTO.Response.LoginResponse;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.persistance.entity.UserDetails;
import com.traders.nst.persistance.repository.UserDetailsRepository;
import com.traders.nst.util.CommonUtilityFunction;
import com.traders.nst.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.traders.nst.enums.ResponseEnum.SUCCESS;
import static com.traders.nst.exception.enums.ResponseEnum.INVALID_USER_CREDS;

@Service
public class LoginService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public ResponseEntity<ResponseDTO<LoginResponse>> login(String username, String password) {
        LoginResponse loginResponse = new LoginResponse();
        UserDetails userDetails = userDetailsRepository.findByUserName(username);
        String uuid =  UUID.randomUUID().toString();
        if (userDetails == null) {
            throw new UsernameNotFoundException(INVALID_USER_CREDS.getMessage());
        }
        else if (!userDetails.getPassword().equals(password)) {
            throw new UsernameNotFoundException(INVALID_USER_CREDS.getMessage());
        }
        String jwtToken = jwtTokenUtil.generateToken(username,uuid);
        loginResponse.setJwtToken(jwtToken);
        loginResponse.setUserName(username);
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(loginResponse,SUCCESS.name()), HttpStatus.OK);
    }
}
