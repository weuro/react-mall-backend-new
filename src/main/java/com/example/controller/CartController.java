package com.example.controller;

import com.example.dto.CartReceive;
import com.example.dto.ResponseResult;
import com.example.service.CartService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Resource
    private CartService cartService;

    @PostMapping("/api/cart/create")
    public ResponseResult createCart(@RequestBody CartReceive cartReceive) {
        return cartService.createCart(cartReceive);
    }

    @PostMapping("/api/cart/update")
    public ResponseResult updateCart(@RequestBody CartReceive cartReceive) {
        return cartService.updateCart(cartReceive);
    }
}
