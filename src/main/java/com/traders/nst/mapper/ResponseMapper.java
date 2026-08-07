package com.traders.nst.mapper;

import com.traders.nst.DTO.Response.ProductListResponse;
import com.traders.nst.persistance.entity.ProductDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ResponseMapper {
    ProductListResponse mapProductEntityToList(ProductDetails productDetails);
    List<ProductListResponse> mapProductEntityToListResponse(List<ProductDetails> productDetails);
}
