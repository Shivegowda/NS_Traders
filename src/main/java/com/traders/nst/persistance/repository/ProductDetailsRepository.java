package com.traders.nst.persistance.repository;

import com.traders.nst.DTO.Request.ProductRequest;
import com.traders.nst.persistance.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    boolean existsByProductNameIgnoreCase(String productName);
    ProductDetails findByProductId(Long productId);
}
