package com.traders.nst.service;

import com.traders.nst.DTO.Request.ProductRequest;
import com.traders.nst.DTO.Response.ProductListResponse;
import com.traders.nst.enums.ActivationStatus;
import com.traders.nst.mapper.RequestMapper;
import com.traders.nst.mapper.ResponseMapper;
import com.traders.nst.persistance.entity.ProductDetails;
import com.traders.nst.persistance.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ResponseMapper responseMapper;

    public List<ProductDetails> getProductDetails() {
        return productDetailsRepository.findAll();
    }

    public ProductDetails addProduct(ProductRequest productRequest) {
        if(!productDetailsRepository.existsByProductNameIgnoreCase(productRequest.getProductName())) {
         ProductDetails productDetails = requestMapper.mapProductDetailsEntity(productRequest);
            productDetails.setProductStatus(ActivationStatus.ACTIVE);
            productDetails.setRateChangeDate(Timestamp.from(Instant.now()));
            productDetailsRepository.save(productDetails);
            return productDetails;
        }
        return null;}

    public ProductDetails updateProduct(ProductRequest productRequest) {
        ProductDetails productDetails = productDetailsRepository.findByProductId(productRequest.getProductId());
        Optional.ofNullable(productRequest.getProductDescription()).ifPresent(productDetails::setProductDescription);
       Optional.ofNullable(productRequest.getProductStatus()).ifPresent(productDetails::setProductStatus);
        if(!ObjectUtils.isEmpty(productRequest.getProductRate())) {
            //TODO :: move the existing record to history table.
            productDetails.setProductRate(productRequest.getProductRate());
            productDetails.setRateChangeDate(Timestamp.from(Instant.now()));
        }
        productDetailsRepository.save(productDetails);
        return productDetails;
    }
    public List<ProductListResponse> getProductList() {
      return responseMapper.mapProductEntityToListResponse(productDetailsRepository.findByProductStatus(ActivationStatus.ACTIVE));
    }
}
