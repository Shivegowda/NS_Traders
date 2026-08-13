package com.traders.nst.Controller;

import com.traders.nst.DTO.Request.FarmerRequest;
import com.traders.nst.DTO.Response.DropDownResponse;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.persistance.entity.FarmerDetails;
import com.traders.nst.service.FarmersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmers")
public class FarmersController {
    @Autowired
    private FarmersService farmersService;

    @GetMapping("/view")
    public ResponseEntity<ResponseDTO<FarmerDetails>> viewFarmers(@RequestParam(required = false) String name) {

            return farmersService.findAll(name);

    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<FarmerDetails>> addFarmer(@Valid @RequestBody FarmerRequest farmerRequest){
        return farmersService.saveFarmer(farmerRequest);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDTO<FarmerDetails>> editFarmer(@RequestBody FarmerRequest farmerRequest){
        return farmersService.updateFarmer(farmerRequest);
    }

    @GetMapping("/dropDownList")
    public ResponseEntity<ResponseDTO<DropDownResponse>> dropDownList(){
        return farmersService.getActiveFarmers();
    }

}
