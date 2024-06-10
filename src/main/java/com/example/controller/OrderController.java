package com.example.controller;

import com.example.dto.OrderCreate;
import com.example.dto.ResponseResult;
import com.example.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/api/order/create")
    public ResponseResult createOrder(@RequestBody OrderCreate orderCreate) {
        return orderService.createOrder(orderCreate);
    }

    @GetMapping("/api/order/getbyid")
    public ResponseResult getOrderById(@RequestParam Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/api/order/pay")
    public ResponseResult payOrder(@RequestBody OrderCreate orderCreate) {
        return orderService.payOrder(orderCreate);
    }

    @GetMapping("/api/order/un-pay")
    public ResponseResult upPayOrder(@RequestParam Long id) {
        return orderService.upPayOrder(id);
    }
}
