package com.traders.nst.service;

import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.persistance.entity.InventoryDetails;
import com.traders.nst.persistance.repository.InventoryDetailsRepository;
import com.traders.nst.util.CommonUtilityFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.traders.nst.enums.ResponseEnum.SUCCESS;

@Service
public class InventoryDetailsService {
    @Autowired
    private InventoryDetailsRepository inventoryDetailsRepository;

    public ResponseEntity<ResponseDTO<InventoryDetails>> getInventoryDetails() {
        List<InventoryDetails> inventoryDetailsList = inventoryDetailsRepository.findAll();
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(inventoryDetailsList,SUCCESS.name()), HttpStatus.OK);
    }
}
