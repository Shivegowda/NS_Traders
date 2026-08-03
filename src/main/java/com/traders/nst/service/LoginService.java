package com.traders.nst.service;

import com.traders.nst.persistance.entity.UserDetails;
import com.traders.nst.persistance.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public String login(String username, String password) {
        UserDetails userDetails = userDetailsRepository.findByUserName(username);
        if (userDetails == null) {
            return "Invalid username or password";
        }
        else if (!userDetails.getPassword().equals(password)) {
            return "Invalid username or password";
        }
        return "Welcome to NST Traders :"+userDetails.getFullName();
    }
}
