package com.traders.nst.service;

import com.traders.nst.persistance.entity.FarmerDetails;
import com.traders.nst.persistance.repository.FarmerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmersService {
    @Autowired
    private FarmerDetailsRepository farmerDetailsRepository;

    public List<FarmerDetails> findAll() {
        return farmerDetailsRepository.findAll();
    }
}
