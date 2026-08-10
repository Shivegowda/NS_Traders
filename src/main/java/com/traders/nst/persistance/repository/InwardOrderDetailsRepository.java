package com.traders.nst.persistance.repository;

import com.traders.nst.persistance.entity.InwardOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InwardOrderDetailsRepository extends JpaRepository<InwardOrderDetails, Long> {

}
