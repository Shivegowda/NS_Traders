package com.traders.nst.service;

import com.traders.nst.DTO.Request.PurchaseOrderRequest;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.enums.OrderType;
import com.traders.nst.exception.NSTCustomException;
import com.traders.nst.mapper.RequestMapper;
import com.traders.nst.persistance.entity.InventoryDetails;
import com.traders.nst.persistance.entity.ProductDetails;
import com.traders.nst.persistance.entity.PurchaseOrderDetails;
import com.traders.nst.persistance.repository.InventoryDetailsRepository;
import com.traders.nst.persistance.repository.ProductDetailsRepository;
import com.traders.nst.persistance.repository.PurchaseOrderDetailsRepository;
import com.traders.nst.util.CommonUtilityFunction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static com.traders.nst.enums.ResponseEnum.SUCCESS;
import static com.traders.nst.exception.enums.ResponseErrorCodeEnum.INVALID_ORDER_REQUEST;
import static com.traders.nst.exception.enums.ResponseErrorCodeEnum.INVALID_PRODUCT_SELECTED;

@Service
public class OrderService {
    @Autowired
    private PurchaseOrderDetailsRepository purchaseOrderDetailsRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private InventoryDetailsRepository inventoryDetailsRepository;

    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> addPurchaseDraftOrder(PurchaseOrderRequest purchaseOrderRequest) {
    PurchaseOrderDetails purchaseOrderDetails = requestMapper.mapInwardOrderDetailsEntity(purchaseOrderRequest);
    purchaseOrderDetails.setOrderType(OrderType.DRAFT);
    purchaseOrderDetails.setCreatedDate(Timestamp.from(Instant.now()));
    purchaseOrderDetailsRepository.save(purchaseOrderDetails);
    return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(purchaseOrderDetails,SUCCESS.name()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> viewPurchaseOrderDetails(OrderType orderType) {
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(purchaseOrderDetailsRepository.findAllByOrderType(orderType), SUCCESS.name()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> updatePurchaseDraftOrderDetails(PurchaseOrderRequest purchaseOrderRequest) {
        PurchaseOrderDetails purchaseOrderDetails = purchaseOrderDetailsRepository.getReferenceById(purchaseOrderRequest.getOrderId());
        if(purchaseOrderDetails.getOrderType().equals(OrderType.ORDER)) {
            return null;
        }
        Optional.ofNullable(purchaseOrderRequest.getProductId()).ifPresent(purchaseOrderDetails::setProductId);
        Optional.ofNullable(purchaseOrderRequest.getProductName()).ifPresent(purchaseOrderDetails::setProductName);
        Optional.ofNullable(purchaseOrderRequest.getQuantity()).ifPresent(purchaseOrderDetails::setQuantity);
        Optional.ofNullable(purchaseOrderRequest.getRate()).ifPresent(purchaseOrderDetails::setRate);
        Optional.ofNullable(purchaseOrderRequest.getAmount()).ifPresent(purchaseOrderDetails::setAmount);
        Optional.ofNullable(purchaseOrderRequest.getOrderedBy()).ifPresent(purchaseOrderDetails::setOrderedBy);
        Optional.ofNullable(purchaseOrderRequest.getOrderedByName()).ifPresent(purchaseOrderDetails::setOrderedByName);
        purchaseOrderDetails.setCreatedDate(Timestamp.from(Instant.now()));
        purchaseOrderDetailsRepository.save(purchaseOrderDetails);
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(purchaseOrderDetails,SUCCESS.name()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> submitPurchaseDraftOrderDetails(PurchaseOrderRequest purchaseOrderRequest) {
        PurchaseOrderDetails purchaseOrderDetails = purchaseOrderDetailsRepository.getReferenceById(purchaseOrderRequest.getOrderId());
        Optional.ofNullable(purchaseOrderRequest.getProductId()).ifPresent(purchaseOrderDetails::setProductId);
        Optional.ofNullable(purchaseOrderRequest.getProductName()).ifPresent(purchaseOrderDetails::setProductName);
        Optional.ofNullable(purchaseOrderRequest.getQuantity()).ifPresent(purchaseOrderDetails::setQuantity);
        Optional.ofNullable(purchaseOrderRequest.getRate()).ifPresent(purchaseOrderDetails::setRate);
        Optional.ofNullable(purchaseOrderRequest.getAmount()).ifPresent(purchaseOrderDetails::setAmount);
        Optional.ofNullable(purchaseOrderRequest.getOrderedBy()).ifPresent(purchaseOrderDetails::setOrderedBy);
        Optional.ofNullable(purchaseOrderRequest.getOrderedByName()).ifPresent(purchaseOrderDetails::setOrderedByName);
        purchaseOrderDetails.setOrderType(OrderType.ORDER);
        purchaseOrderDetails.setCreatedDate(Timestamp.from(Instant.now()));
        purchaseOrderDetailsRepository.save(purchaseOrderDetails);
        calculateQuantity(purchaseOrderDetails);
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(purchaseOrderDetails,SUCCESS.name()), HttpStatus.OK);
    }

    @Transactional
    private void calculateQuantity(PurchaseOrderDetails purchaseOrderDetails) {
        Optional<InventoryDetails> inventoryDetails = inventoryDetailsRepository.findById(purchaseOrderDetails.getProductId());
        if(inventoryDetails.isPresent()) {
           Double updatedNetQuantity = inventoryDetails.get().getNetQuantity() + purchaseOrderDetails.getQuantity();
            Double updatedPurchasedQuantity = inventoryDetails.get().getPurchasedQuantity() + purchaseOrderDetails.getQuantity();

            inventoryDetails.get().setNetQuantity(updatedNetQuantity);
            inventoryDetails.get().setPurchasedQuantity(updatedPurchasedQuantity);
            inventoryDetailsRepository.save(inventoryDetails.get());
        }
        else {
            InventoryDetails newInventoryDetails = new InventoryDetails();
            newInventoryDetails.setProductId(purchaseOrderDetails.getProductId());
            newInventoryDetails.setProductName(purchaseOrderDetails.getProductName());
            newInventoryDetails.setNetQuantity(purchaseOrderDetails.getQuantity());
            newInventoryDetails.setPurchasedQuantity(purchaseOrderDetails.getQuantity());
            inventoryDetailsRepository.save(newInventoryDetails);
        }
    }

    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> deletePurchaseDraftOrderDetails(Long orderId) {
        PurchaseOrderDetails purchaseOrderDetails = purchaseOrderDetailsRepository.getReferenceById(orderId);
        if(purchaseOrderDetails.getOrderType().equals(OrderType.ORDER)) {
            throw new NSTCustomException(INVALID_ORDER_REQUEST.getErrorCode(),INVALID_ORDER_REQUEST.getMessage());
        }
        purchaseOrderDetailsRepository.delete(purchaseOrderDetails);
        return new ResponseEntity<>(CommonUtilityFunction.mapToResponseDTO(purchaseOrderDetails,SUCCESS.name()), HttpStatus.OK);
    }


}
