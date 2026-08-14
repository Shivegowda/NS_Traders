package com.traders.nst.persistance.repository;

import com.traders.nst.persistance.entity.BuyerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerDetailsRepository extends JpaRepository<BuyerDetails, Long> {

}
