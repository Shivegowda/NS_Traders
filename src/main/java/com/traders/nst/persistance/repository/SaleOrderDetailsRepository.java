package com.traders.nst.persistance.repository;

import com.traders.nst.enums.OrderType;
import com.traders.nst.persistance.entity.SaleOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleOrderDetailsRepository extends JpaRepository<SaleOrderDetails, Long> {
    List<SaleOrderDetails> findAllByOrderType(OrderType orderType);
}
