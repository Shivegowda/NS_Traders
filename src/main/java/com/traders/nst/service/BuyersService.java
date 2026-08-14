package com.traders.nst.service;

import com.traders.nst.DTO.Request.BuyerRequest;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.enums.ActivationStatus;
import com.traders.nst.mapper.RequestMapper;
import com.traders.nst.persistance.entity.BuyerDetails;
import com.traders.nst.persistance.repository.BuyerDetailsRepository;
import com.traders.nst.util.CommonUtilityFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.traders.nst.enums.ResponseEnum.SUCCESS;

@Service
public class BuyersService {
    @Autowired
    private BuyerDetailsRepository buyerDetailsRepository;

    @Autowired
    private RequestMapper requestMapper;

    public ResponseEntity<ResponseDTO<BuyerDetails>> getBuyerDetails() {
       List<BuyerDetails> buyerDetailsList = buyerDetailsRepository.findAll();
       return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(buyerDetailsList,SUCCESS.name()), HttpStatus.OK);

    }
     public ResponseEntity<ResponseDTO<BuyerDetails>> addNewBuyer(BuyerRequest buyerRequest) {
        BuyerDetails buyerDetails = requestMapper.mapBuyerDetailsEntity(buyerRequest);
        buyerDetails.setStatus(ActivationStatus.ACTIVE);
        buyerDetails.setCreatedDate(Timestamp.from(Instant.now()));
        BuyerDetails buyerDetailsEntity = buyerDetailsRepository.save(buyerDetails);
        return ResponseEntity.ok(CommonUtilityFunction.mapToResponseDTO(buyerDetailsEntity,SUCCESS.name()));
     }

     public ResponseEntity<ResponseDTO<BuyerDetails>> updateBuyerDetails(BuyerRequest buyerRequest) {
        BuyerDetails buyerDetails = buyerDetailsRepository.findById(buyerRequest.getBuyerId()).orElse(null);
        if (buyerDetails == null) {
            return null;
        }
        else {
            Optional.ofNullable(buyerRequest.getBuyerName()).ifPresent(buyerDetails::setBuyerName);
            Optional.ofNullable(buyerRequest.getAddress()).ifPresent(buyerDetails::setAddress);
            Optional.ofNullable(buyerRequest.getMobileNumber()).ifPresent(buyerDetails::setMobileNumber);
            buyerDetailsRepository.save(buyerDetails);
            return ResponseEntity.ok(CommonUtilityFunction.mapToResponseDTO(buyerDetails, SUCCESS.name()));
        }
     }

     public ResponseEntity<ResponseDTO<BuyerDetails>> deleteBuyerDetails( long id) {
         BuyerDetails buyerDetails = buyerDetailsRepository.findById(id).orElse(null);
    if (buyerDetails == null) {
        return null;
    }
    else{
        buyerDetails.setStatus(ActivationStatus.INACTIVE);
        buyerDetailsRepository.save(buyerDetails);
        return ResponseEntity.ok(CommonUtilityFunction.mapToResponseDTO(buyerDetails, SUCCESS.name()));
    }
     }
}
