package com.traders.nst.Controller;

import com.traders.nst.DTO.Request.PurchaseOrderRequest;
import com.traders.nst.DTO.common.ResponseDTO;
import com.traders.nst.enums.OrderType;
import com.traders.nst.persistance.entity.PurchaseOrderDetails;
import com.traders.nst.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/purchase/add")
    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> addPurchaseDraftOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest) {
      return  orderService.addPurchaseDraftOrder(purchaseOrderRequest);
    }

    @GetMapping("/purchase/view")
    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> viewPurchaseDraftOrderDetails(@RequestParam OrderType orderType) {
        return orderService.viewPurchaseOrderDetails(orderType);
    }

    @PutMapping("/purchase/update")
    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> updatePurchaseDraftOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest) {
        return orderService.updatePurchaseDraftOrderDetails(purchaseOrderRequest);
    }

    @PostMapping("/purchase/submit")
    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> submitPurchaseDraftOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest) {
        return orderService.submitPurchaseDraftOrderDetails(purchaseOrderRequest);
    }

    @DeleteMapping("/purchase/delete")
    public ResponseEntity<ResponseDTO<PurchaseOrderDetails>> deletePurchaseDraftOrder(@RequestParam Long orderId) {
        return orderService.deletePurchaseDraftOrderDetails(orderId);
    }

}
