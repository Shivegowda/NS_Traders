package com.traders.nst.Controller;

import com.traders.nst.DTO.Request.SaleOrderRequest;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.enums.OrderType;
import com.traders.nst.persistance.entity.SaleOrderDetails;
import com.traders.nst.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/sale")
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @GetMapping("/view")
    public ResponseEntity<ResponseDTO<SaleOrderDetails>> getSaleOrderDetails(@RequestParam OrderType orderType) {
        return saleOrderService.getAllSaleDraftOrders(orderType);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<SaleOrderDetails>> createSaleOrderDraft(@RequestBody SaleOrderRequest saleOrderRequest) {
        return saleOrderService.createSaleOrderDraft(saleOrderRequest);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<SaleOrderDetails>> updateSaleOrderDraft(@RequestBody SaleOrderRequest saleOrderRequest) {
        return saleOrderService.updateSaleOrderDraft(saleOrderRequest);
    }

    @PostMapping("/submit")
    public ResponseEntity<ResponseDTO<SaleOrderDetails>> submitSaleOrderDraft(@RequestBody SaleOrderRequest saleOrderRequest) {
        return saleOrderService.submitSaleOrder( saleOrderRequest );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO<SaleOrderDetails>> deleteSaleOrderDraft(@RequestParam Long orderId) {
        return saleOrderService.deleteSaleOrder(orderId);
    }

}
