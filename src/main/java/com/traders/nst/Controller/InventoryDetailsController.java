package com.traders.nst.Controller;

import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.persistance.entity.InventoryDetails;
import com.traders.nst.service.InventoryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryDetailsController {
    @Autowired
    private InventoryDetailsService inventoryDetailsService;

    @GetMapping("/view")
    public ResponseEntity<ResponseDTO<InventoryDetails>> getInventoryDetails() {
        return inventoryDetailsService.getInventoryDetails();
    }
}
