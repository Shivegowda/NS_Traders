package com.traders.nst.persistance.repository;

import com.traders.nst.enums.ActivationStatus;
import com.traders.nst.persistance.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    boolean existsByProductNameIgnoreCase(String productName);
    ProductDetails findByProductId(Long productId);
    List<ProductDetails> findByProductStatus(ActivationStatus activationStatus);
}
