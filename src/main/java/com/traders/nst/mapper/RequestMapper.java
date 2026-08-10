package com.traders.nst.mapper;

import com.traders.nst.DTO.Request.FarmerRequest;
import com.traders.nst.DTO.Request.InwardOrderRequest;
import com.traders.nst.DTO.Request.ProductRequest;
import com.traders.nst.persistance.entity.FarmerDetails;
import com.traders.nst.persistance.entity.InwardOrderDetails;
import com.traders.nst.persistance.entity.ProductDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface RequestMapper {

    FarmerDetails mapFarmerDetailsEntity(FarmerRequest farmerRequest);

    ProductDetails mapProductDetailsEntity(ProductRequest productRequest);

    InwardOrderDetails mapInwardOrderDetailsEntity(InwardOrderRequest inwardOrderRequest);
}
