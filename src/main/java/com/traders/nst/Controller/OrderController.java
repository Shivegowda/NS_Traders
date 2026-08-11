package com.traders.nst.Controller;

import com.traders.nst.DTO.Request.PurchaseOrderRequest;
import com.traders.nst.enums.OrderType;
import com.traders.nst.persistance.entity.PurchaseOrderDetails;
import com.traders.nst.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/purchase/add")
    public PurchaseOrderDetails addPurchaseDraftOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest) {
      return  orderService.addPurchaseDraftOrder(purchaseOrderRequest);
    }

    @GetMapping("/purchase/view")
    public List<PurchaseOrderDetails> viewPurchaseDraftOrderDetails(@RequestParam OrderType orderType) {
        return orderService.viewPurchaseOrderDetails(orderType);
    }

    @PutMapping("/purchase/update")
    public PurchaseOrderDetails updatePurchaseDraftOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest) {
        return orderService.updatePurchaseDraftOrderDetails(purchaseOrderRequest);
    }

    @PostMapping("/purchase/submit")
    public PurchaseOrderDetails submitPurchaseDraftOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest) {
        return orderService.submitPurchaseDraftOrderDetails(purchaseOrderRequest);
    }

    @DeleteMapping("/purchase/delete")
    public PurchaseOrderDetails deletePurchaseDraftOrder(@RequestParam Long orderId) {
        return orderService.deletePurchaseDraftOrderDetails(orderId);
    }

}
