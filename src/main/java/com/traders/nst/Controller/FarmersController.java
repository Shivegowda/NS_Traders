package com.traders.nst.Controller;

import com.traders.nst.persistance.entity.FarmerDetails;
import com.traders.nst.service.FarmersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/farmers")
public class FarmersController {
    @Autowired
    private FarmersService farmersService;

    @GetMapping("/view")
    public List<FarmerDetails> viewFarmers(){
        return farmersService.findAll();
    }
}
