package com.traders.nst.service;

import com.traders.nst.DTO.Request.ProductRequest;
import com.traders.nst.DTO.Response.ProductListResponse;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.enums.ActivationStatus;
import com.traders.nst.exception.NSTCustomException;
import com.traders.nst.mapper.RequestMapper;
import com.traders.nst.mapper.ResponseMapper;
import com.traders.nst.persistance.entity.ProductDetails;
import com.traders.nst.persistance.entity.ProductRateHistory;
import com.traders.nst.persistance.repository.ProductDetailsRepository;
import com.traders.nst.persistance.repository.ProductRateHistoryRepository;
import com.traders.nst.util.CommonUtilityFunction;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.traders.nst.enums.ResponseEnum.SUCCESS;
import static com.traders.nst.exception.enums.ResponseErrorCodeEnum.PRODUCT_ALREADY_EXISTS;

@Service
public class ProductService {
    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private ProductRateHistoryRepository productRateHistoryRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ResponseMapper responseMapper;

    public ResponseEntity<ResponseDTO<ProductDetails>> getProductDetails() {

        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(productDetailsRepository.findAll(),SUCCESS.name()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<ProductDetails>> addProduct(ProductRequest productRequest) {
        if(!productDetailsRepository.existsByProductNameIgnoreCase(productRequest.getProductName())) {
         ProductDetails productDetails = requestMapper.mapProductDetailsEntity(productRequest);
            productDetails.setProductStatus(ActivationStatus.ACTIVE);
            productDetails.setRateChangeDate(Timestamp.from(Instant.now()));
            productDetailsRepository.save(productDetails);
            return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(productDetails, SUCCESS.name()), HttpStatus.OK);
        }
        throw new NSTCustomException(PRODUCT_ALREADY_EXISTS.getErrorCode(),PRODUCT_ALREADY_EXISTS.getMessage());
    }

    public ResponseEntity<ResponseDTO<ProductDetails>> updateProduct(ProductRequest productRequest) {
        ProductDetails productDetails = productDetailsRepository.findByProductId(productRequest.getProductId());
        Optional.ofNullable(productRequest.getProductDescription()).ifPresent(productDetails::setProductDescription);
        if(!ObjectUtils.isEmpty(productRequest.getProductRate())) {
            ProductRateHistory productRateHistory = new ProductRateHistory();
            productRateHistory.setProductId(productRequest.getProductId());
            productRateHistory.setRate(productDetails.getProductRate());
            productRateHistory.setEffectiveDate(productDetails.getRateChangeDate());
            productRateHistoryRepository.save(productRateHistory);
            productDetails.setProductRate(productRequest.getProductRate());
            productDetails.setRateChangeDate(Timestamp.from(Instant.now()));
        }
        productDetailsRepository.save(productDetails);
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(productDetails, SUCCESS.name()), HttpStatus.OK);
    }
    public List<ProductListResponse> getProductList() {
      return responseMapper.mapProductEntityToListResponse(productDetailsRepository.findByProductStatus(ActivationStatus.ACTIVE));
    }

    public ResponseEntity<ResponseDTO<ProductDetails>> markActiveInactiveProduct(ProductRequest productRequest) {
       ProductDetails productDetails = productDetailsRepository.findByProductId(productRequest.getProductId());
        ProductRateHistory productRateHistory = new ProductRateHistory();
        if(productRequest.getProductStatus().equals(ActivationStatus.ACTIVE)) {
            productRateHistory.setProductId(productRequest.getProductId());
            productRateHistory.setRate(productDetails.getProductRate());
            productRateHistory.setEffectiveDate(productDetails.getRateChangeDate());
            productRateHistoryRepository.save(productRateHistory);
            productDetails.setProductStatus(ActivationStatus.ACTIVE);
            productDetails.setProductRate(productRequest.getProductRate());
            productDetails.setRateChangeDate(Timestamp.from(Instant.now()));
            productDetailsRepository.save(productDetails);
        }
        else{
            productRateHistory.setProductId(productRequest.getProductId());
            productRateHistory.setRate(productDetails.getProductRate());
            productRateHistory.setEffectiveDate(productDetails.getRateChangeDate());
            productRateHistoryRepository.save(productRateHistory);
            productDetails.setProductStatus(ActivationStatus.INACTIVE);
            productDetails.setRateChangeDate(Timestamp.from(Instant.now()));
            productDetails.setProductRate(0.00);
            productDetailsRepository.save(productDetails);
        }
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(productDetails, SUCCESS.name()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<ProductRateHistory>> getProductRateHistory(Long productId) {
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(productRateHistoryRepository.findAllByProductIdOrderByEffectiveDateDesc(productId), SUCCESS.name()),HttpStatus.OK);
    }
}
