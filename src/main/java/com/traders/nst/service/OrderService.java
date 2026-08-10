package com.traders.nst.service;

import com.traders.nst.DTO.Request.InwardOrderRequest;
import com.traders.nst.enums.OrderType;
import com.traders.nst.mapper.RequestMapper;
import com.traders.nst.persistance.entity.InwardOrderDetails;
import com.traders.nst.persistance.repository.InwardOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private InwardOrderDetailsRepository inwardOrderDetailsRepository;

    @Autowired
    private RequestMapper requestMapper;

    public InwardOrderDetails addNewOrder(InwardOrderRequest inwardOrderRequest) {
    InwardOrderDetails inwardOrderDetails = requestMapper.mapInwardOrderDetailsEntity(inwardOrderRequest);
    inwardOrderDetails.setOrderType(OrderType.DRAFT);
    inwardOrderDetails.setCreatedDate(Timestamp.from(Instant.now()));
    inwardOrderDetailsRepository.save(inwardOrderDetails);
    return inwardOrderDetails;
    }

    public List<InwardOrderDetails> viewInwardOrderDetails() {
        return inwardOrderDetailsRepository.findAll();
    }
}
