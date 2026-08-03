package com.traders.nst.Controller;

import com.traders.nst.persistance.entity.UserDetails;
import com.traders.nst.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping
    public String userLogin(@RequestBody UserDetails userDetails) {
return  loginService.login(userDetails.getUserName(), userDetails.getPassword());
    }
}
