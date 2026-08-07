package com.traders.nst.Controller;

import com.traders.nst.DTO.Request.ProductRequest;
import com.traders.nst.DTO.Response.ProductListResponse;
import com.traders.nst.persistance.entity.ProductDetails;
import com.traders.nst.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/view")
    List<ProductDetails> getProductDetails() {
        return productService.getProductDetails();
    }

    @PostMapping("/add")
    public ProductDetails addProductDetails(@Valid @RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @PutMapping("/edit")
    public ProductDetails updateProduct(@RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productRequest);
    }

    @GetMapping("/list")
    public List<ProductListResponse>  getProductList() {

    }

}
