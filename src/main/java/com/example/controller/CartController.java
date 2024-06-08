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

    // 修改购物车中的商品数量
    @PostMapping("/api/cart/update")
    public ResponseResult updateCart(@RequestBody CartReceive cartReceive) {
        return cartService.updateCart(cartReceive);
    }

    @GetMapping("/api/cart/list")
    public ResponseResult listCart(@RequestParam Long user_id) {
        return cartService.listCart(user_id);
    }

    @GetMapping("/api/cart/delete")
    public ResponseResult deleteCart(@RequestParam Long id) {
        return cartService.deleteCart(id);
    }
}
