package com.traders.nst.mapper;

import com.traders.nst.DTO.Request.*;
import com.traders.nst.persistance.entity.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface RequestMapper {

    FarmerDetails mapFarmerDetailsEntity(FarmerRequest farmerRequest);

    ProductDetails mapProductDetailsEntity(ProductRequest productRequest);

    PurchaseOrderDetails mapInwardOrderDetailsEntity(PurchaseOrderRequest purchaseOrderRequest);

    BuyerDetails mapBuyerDetailsEntity(BuyerRequest buyerRequest);

    SaleOrderDetails mapSaleOrderDetailsEntity(SaleOrderRequest saleOrderRequest);
}
