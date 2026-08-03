package com.traders.nst.persistance.repository;

import com.traders.nst.persistance.entity.FarmerDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmerDetailsRepository extends CrudRepository<FarmerDetails, Integer> {
     List<FarmerDetails> findAll();
}
