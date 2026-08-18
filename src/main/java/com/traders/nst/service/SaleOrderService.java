package com.traders.nst.service;

import com.traders.nst.DTO.Request.SaleOrderRequest;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.enums.OrderType;
import com.traders.nst.exception.NSTCustomException;
import com.traders.nst.mapper.RequestMapper;
import com.traders.nst.persistance.entity.InventoryDetails;
import com.traders.nst.persistance.entity.SaleOrderDetails;
import com.traders.nst.persistance.repository.InventoryDetailsRepository;
import com.traders.nst.persistance.repository.SaleOrderDetailsRepository;
import com.traders.nst.util.CommonUtilityFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.traders.nst.enums.ResponseEnum.SUCCESS;
import static com.traders.nst.exception.enums.ResponseErrorCodeEnum.*;

@Service
public class SaleOrderService {
    @Autowired
    private SaleOrderDetailsRepository saleOrderDetailsRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private InventoryDetailsRepository inventoryDetailsRepository;


    public ResponseEntity<ResponseDTO<SaleOrderDetails>> getAllSaleDraftOrders(OrderType orderType) {
        List<SaleOrderDetails> saleOrderDetailsList = saleOrderDetailsRepository.findAllByOrderType(orderType);
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(saleOrderDetailsList, SUCCESS.name()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<SaleOrderDetails>> createSaleOrderDraft(SaleOrderRequest saleOrderRequest) {
        SaleOrderDetails saleOrderDetails = requestMapper.mapSaleOrderDetailsEntity(saleOrderRequest);
        saleOrderDetails.setOrderType(OrderType.DRAFT);
        saleOrderDetails.setCreatedDate(Timestamp.from(Instant.now()));
        saleOrderDetailsRepository.save(saleOrderDetails);
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(saleOrderDetails, SUCCESS.name()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<SaleOrderDetails>> updateSaleOrderDraft(SaleOrderRequest saleOrderRequest) {
        SaleOrderDetails saleOrderDetails = saleOrderDetailsRepository.getReferenceById(saleOrderRequest.getOrderId());
        if(saleOrderDetails.getOrderType().equals(OrderType.DRAFT)) {
            Optional.ofNullable(saleOrderRequest.getProductId()).ifPresent(saleOrderDetails::setProductId);
            Optional.ofNullable(saleOrderRequest.getProductName()).ifPresent(saleOrderDetails::setProductName);
            Optional.ofNullable(saleOrderRequest.getQuantity()).ifPresent(saleOrderDetails::setQuantity);
            Optional.ofNullable(saleOrderRequest.getRate()).ifPresent(saleOrderDetails::setRate);
            Optional.ofNullable(saleOrderRequest.getAmount()).ifPresent(saleOrderDetails::setAmount);
            Optional.ofNullable(saleOrderRequest.getOrderedBy()).ifPresent(saleOrderDetails::setOrderedBy);
            Optional.ofNullable(saleOrderRequest.getOrderedByName()).ifPresent(saleOrderDetails::setOrderedByName);
            saleOrderDetails.setCreatedDate(Timestamp.from(Instant.now()));
            saleOrderDetailsRepository.save(saleOrderDetails);
            return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(saleOrderDetails, SUCCESS.name()), HttpStatus.OK);
        }
        throw new NSTCustomException(SERVER_ERROR.getErrorCode(),SERVER_ERROR.getMessage());
    }

    public ResponseEntity<ResponseDTO<SaleOrderDetails>> submitSaleOrder(SaleOrderRequest saleOrderRequest) {
        SaleOrderDetails saleOrderDetails = saleOrderDetailsRepository.getReferenceById(saleOrderRequest.getOrderId());
        Optional.ofNullable(saleOrderRequest.getProductId()).ifPresent(saleOrderDetails::setProductId);
        Optional.ofNullable(saleOrderRequest.getProductName()).ifPresent(saleOrderDetails::setProductName);
        Optional.ofNullable(saleOrderRequest.getQuantity()).ifPresent(saleOrderDetails::setQuantity);
        Optional.ofNullable(saleOrderRequest.getRate()).ifPresent(saleOrderDetails::setRate);
        Optional.ofNullable(saleOrderRequest.getAmount()).ifPresent(saleOrderDetails::setAmount);
        Optional.ofNullable(saleOrderRequest.getOrderedBy()).ifPresent(saleOrderDetails::setOrderedBy);
        Optional.ofNullable(saleOrderRequest.getOrderedByName()).ifPresent(saleOrderDetails::setOrderedByName);
        saleOrderDetails.setOrderType(OrderType.ORDER);
        saleOrderDetails.setCreatedDate(Timestamp.from(Instant.now()));
        saleOrderDetailsRepository.save(saleOrderDetails);
        calculateQuantity(saleOrderDetails);
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(saleOrderDetails, SUCCESS.name()), HttpStatus.OK);
    }

    private void calculateQuantity(SaleOrderDetails saleOrderDetails) {
        InventoryDetails inventoryDetails = inventoryDetailsRepository.findById(saleOrderDetails.getProductId()).orElse(null);
        if(inventoryDetails != null) {
            Double updatedQuantity = inventoryDetails.getNetQuantity() - saleOrderDetails.getQuantity();
            inventoryDetails.setNetQuantity(updatedQuantity);
            Double updatedSoldQuantity = inventoryDetails.getSoldQuantity() + saleOrderDetails.getQuantity();
            inventoryDetails.setSoldQuantity(updatedSoldQuantity);
            inventoryDetailsRepository.save(inventoryDetails);
        }
        else {
            throw new NSTCustomException(INVALID_PRODUCT_SELECTED.getErrorCode(),INVALID_PRODUCT_SELECTED.getMessage());
        }
    }

    public ResponseEntity<ResponseDTO<SaleOrderDetails>> deleteSaleOrder(Long orderId) {
        SaleOrderDetails saleOrderDetails = saleOrderDetailsRepository.getReferenceById(orderId);
        if(saleOrderDetails.getOrderType().equals(OrderType.DRAFT)) {
            saleOrderDetailsRepository.delete(saleOrderDetails);
            return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(saleOrderDetails, SUCCESS.name()), HttpStatus.OK);
        }
        else {
            throw new NSTCustomException(INVALID_ORDER_REQUEST.getErrorCode(),INVALID_ORDER_REQUEST.getMessage());
        }
    }

}
