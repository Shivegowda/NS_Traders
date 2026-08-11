package com.traders.nst.persistance.repository;

import com.traders.nst.enums.OrderType;
import com.traders.nst.persistance.entity.PurchaseOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderDetailsRepository extends JpaRepository<PurchaseOrderDetails, Long> {

    List<PurchaseOrderDetails> findAllByOrderType(OrderType orderType);
}
