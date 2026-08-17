package com.traders.nst.service;

import com.traders.nst.DTO.Request.FarmerRequest;
import com.traders.nst.DTO.Response.DropDownResponse;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.enums.ActivationStatus;
import com.traders.nst.mapper.RequestMapper;
import com.traders.nst.persistance.entity.FarmerDetails;
import com.traders.nst.persistance.repository.FarmerDetailsRepository;
import com.traders.nst.util.CommonUtilityFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.traders.nst.enums.ResponseEnum.SUCCESS;

@Service
public class FarmersService {
    @Autowired
    private FarmerDetailsRepository farmerDetailsRepository;

    @Autowired
    private RequestMapper requestMapper;

    public ResponseEntity<ResponseDTO<FarmerDetails>> findAll(String name) {
        if(null==name || name.isEmpty()) {
            return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(farmerDetailsRepository.findAll(),SUCCESS.name()),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(farmerDetailsRepository.findByFarmerNameLike("%"+name+"%"),SUCCESS.name()),HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseDTO<FarmerDetails>> saveFarmer(FarmerRequest farmerRequest) {
        FarmerDetails farmerDetails = requestMapper.mapFarmerDetailsEntity(farmerRequest);
        farmerDetails.setStatus(ActivationStatus.ACTIVE);
        farmerDetails.setCreatedDate(Timestamp.from(Instant.now()));
         farmerDetailsRepository.save(farmerDetails);
         return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(farmerDetails,SUCCESS.name()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<FarmerDetails>> updateFarmer(FarmerRequest farmerRequest) {
        FarmerDetails farmerDetails = farmerDetailsRepository.findByFarmerId(farmerRequest.getFarmerId());
        Optional.ofNullable(farmerRequest.getFarmerName()).ifPresent(farmerDetails::setFarmerName);
        Optional.ofNullable(farmerRequest.getAddress()).ifPresent(farmerDetails::setAddress);
        Optional.ofNullable(farmerRequest.getStatus()).ifPresent(farmerDetails::setStatus);
        Optional.ofNullable(farmerRequest.getMobileNumber()).ifPresent(farmerDetails::setMobileNumber);
        farmerDetailsRepository.save(farmerDetails);

        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(farmerDetails,SUCCESS.name()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<DropDownResponse>> getActiveFarmers()  {
        List<FarmerDetails> farmerDetails = farmerDetailsRepository.findByStatus(ActivationStatus.ACTIVE);
        List<DropDownResponse> activeFarmers = farmerDetails.stream()
                .map(f-> new DropDownResponse(f.getFarmerId().toString(),f.getFarmerName()+ "-"+f.getMobileNumber()))
                .toList();

        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(activeFarmers,SUCCESS.name()),HttpStatus.OK);
    }
}
