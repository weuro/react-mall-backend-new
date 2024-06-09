package com.example.controller;

import com.example.dto.OrderCreate;
import com.example.dto.ResponseResult;
import com.example.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/api/order/create")
    public ResponseResult createOrder(@RequestBody OrderCreate orderCreate) {
        return orderService.createOrder(orderCreate);
    }
}
