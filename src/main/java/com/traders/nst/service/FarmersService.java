package com.traders.nst.service;

import com.traders.nst.DTO.Request.FarmerRequest;
import com.traders.nst.enums.FarmerStatus;
import com.traders.nst.mapper.RequestMapper;
import com.traders.nst.persistance.entity.FarmerDetails;
import com.traders.nst.persistance.repository.FarmerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FarmersService {
    @Autowired
    private FarmerDetailsRepository farmerDetailsRepository;

    @Autowired
    private RequestMapper requestMapper;

    public List<FarmerDetails> findAll(String name) {
        if(null==name || name.isEmpty()) {
            return farmerDetailsRepository.findAll();
        }
        else{
          return  farmerDetailsRepository.findByFarmerNameLike("%"+name+"%");
        }
    }

    public FarmerDetails saveFarmer(FarmerRequest farmerRequest) {
        FarmerDetails farmerDetails = requestMapper.mapFarmerDetailsEntity(farmerRequest);
        farmerDetails.setStatus(FarmerStatus.ACTIVE);
        farmerDetails.setCreatedDate(Timestamp.from(Instant.now()));
         farmerDetailsRepository.save(farmerDetails);
         return farmerDetails;
    }

    public FarmerDetails updateFarmer(FarmerRequest farmerRequest) {
        FarmerDetails farmerDetails = farmerDetailsRepository.findByFarmerId(farmerRequest.getFarmerId());
        Optional.ofNullable(farmerRequest.getFarmerName()).ifPresent(farmerDetails::setFarmerName);
        Optional.ofNullable(farmerRequest.getAddress()).ifPresent(farmerDetails::setAddress);
        Optional.ofNullable(farmerRequest.getStatus()).ifPresent(farmerDetails::setStatus);
        Optional.ofNullable(farmerRequest.getMobileNumber()).ifPresent(farmerDetails::setMobileNumber);
        farmerDetailsRepository.save(farmerDetails);

        return farmerDetails;
    }
}
