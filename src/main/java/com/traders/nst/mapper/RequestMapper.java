package com.traders.nst.mapper;

import com.traders.nst.DTO.Request.FarmerRequest;
import com.traders.nst.persistance.entity.FarmerDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface RequestMapper {

    FarmerDetails mapFarmerDetailsEntity(FarmerRequest farmerRequest);
}
