package com.traders.nst.persistance.repository;

import com.traders.nst.persistance.entity.InventoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDetailsRepository extends JpaRepository<InventoryDetails, Long> {
    InventoryDetails findById(long id);
}
