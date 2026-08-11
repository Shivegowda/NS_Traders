package com.traders.nst.service;

import com.traders.nst.DTO.Request.PurchaseOrderRequest;
import com.traders.nst.enums.OrderType;
import com.traders.nst.mapper.RequestMapper;
import com.traders.nst.persistance.entity.PurchaseOrderDetails;
import com.traders.nst.persistance.repository.PurchaseOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private PurchaseOrderDetailsRepository purchaseOrderDetailsRepository;

    @Autowired
    private RequestMapper requestMapper;

    public PurchaseOrderDetails addPurchaseDraftOrder(PurchaseOrderRequest purchaseOrderRequest) {
    PurchaseOrderDetails purchaseOrderDetails = requestMapper.mapInwardOrderDetailsEntity(purchaseOrderRequest);
    purchaseOrderDetails.setOrderType(OrderType.DRAFT);
    purchaseOrderDetails.setCreatedDate(Timestamp.from(Instant.now()));
    purchaseOrderDetailsRepository.save(purchaseOrderDetails);
    return purchaseOrderDetails;
    }

    public List<PurchaseOrderDetails> viewPurchaseOrderDetails(OrderType orderType) {
        return purchaseOrderDetailsRepository.findAllByOrderType(orderType);
    }

    public PurchaseOrderDetails updatePurchaseDraftOrderDetails(PurchaseOrderRequest purchaseOrderRequest) {
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
        return purchaseOrderDetails;
    }

    public PurchaseOrderDetails submitPurchaseDraftOrderDetails(PurchaseOrderRequest purchaseOrderRequest) {
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
        return purchaseOrderDetails;
    }

    public PurchaseOrderDetails deletePurchaseDraftOrderDetails(Long orderId) {
        PurchaseOrderDetails purchaseOrderDetails = purchaseOrderDetailsRepository.getReferenceById(orderId);
        if(purchaseOrderDetails.getOrderType().equals(OrderType.ORDER)) {
            return null;
        }
        purchaseOrderDetailsRepository.delete(purchaseOrderDetails);
        return purchaseOrderDetails;
    }
}
