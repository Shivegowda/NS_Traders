package com.traders.nst.persistance.repository;

import com.traders.nst.enums.ActivationStatus;
import com.traders.nst.persistance.entity.FarmerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmerDetailsRepository extends JpaRepository<FarmerDetails, Integer> {
     List<FarmerDetails> findAll();
     List<FarmerDetails> findByFarmerNameLike(String name);
     FarmerDetails findByFarmerId(Long id);
     List<FarmerDetails> findByStatus(ActivationStatus status);
}
