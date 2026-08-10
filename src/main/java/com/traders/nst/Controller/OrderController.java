package com.traders.nst.Controller;

import com.traders.nst.DTO.Request.InwardOrderRequest;
import com.traders.nst.persistance.entity.InwardOrderDetails;
import com.traders.nst.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/inward/add")
    public InwardOrderDetails addInwardOrder(@RequestBody InwardOrderRequest inwardOrderRequest) {
      return  orderService.addNewOrder(inwardOrderRequest);
    }

    @GetMapping("/inward/view")
    public List<InwardOrderDetails> viewInwardOrderDetails() {
        return orderService.viewInwardOrderDetails();
    }
}
