package com.traders.nst.Controller;

import com.traders.nst.DTO.Request.ProductRequest;
import com.traders.nst.DTO.Response.ProductListResponse;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.persistance.entity.ProductDetails;
import com.traders.nst.persistance.entity.ProductRateHistory;
import com.traders.nst.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/view")
    public ResponseEntity<ResponseDTO<ProductDetails>> getProductDetails() {
        return productService.getProductDetails();
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<ProductDetails>> addProductDetails(@Valid @RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDTO<ProductDetails>> updateProduct(@RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productRequest);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO<ProductListResponse>>  getProductList() {
    return productService.getProductList();
    }

    @PutMapping("/active")
    public ResponseEntity<ResponseDTO<ProductDetails>> activeProduct(@RequestBody ProductRequest productRequest) {
        return productService.markActiveInactiveProduct(productRequest);
    }

    @GetMapping("/history")
    public ResponseEntity<ResponseDTO<ProductRateHistory>> getProductHistory(@RequestParam(required = true) Long productId) {
        return productService.getProductRateHistory(productId);
    }
}
