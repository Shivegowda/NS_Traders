package com.traders.nst.Controller;

import com.traders.nst.DTO.Request.BuyerRequest;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.persistance.entity.BuyerDetails;
import com.traders.nst.service.BuyersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buyers")
public class BuyersController {

    @Autowired
    private BuyersService buyersService;

    @GetMapping("/view")
    public ResponseEntity<ResponseDTO<BuyerDetails>> viewBuyerDetails() {
        return buyersService.getBuyerDetails();
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<BuyerDetails>> addBuyerDetails(@RequestBody BuyerRequest buyerRequest) {
        return buyersService.addNewBuyer(buyerRequest);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<BuyerDetails>> updateBuyerDetails(@RequestBody BuyerRequest buyerRequest) {
        return buyersService.updateBuyerDetails(buyerRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO<BuyerDetails>> deleteBuyerDetails(@RequestParam long id) {
        return buyersService.deleteBuyerDetails(id);
    }
}
