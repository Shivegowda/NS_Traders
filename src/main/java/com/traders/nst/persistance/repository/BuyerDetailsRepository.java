package com.traders.nst.persistance.repository;

import com.traders.nst.enums.ActivationStatus;
import com.traders.nst.persistance.entity.BuyerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerDetailsRepository extends JpaRepository<BuyerDetails, Long> {

    List<BuyerDetails> findByStatus(ActivationStatus status);
}
