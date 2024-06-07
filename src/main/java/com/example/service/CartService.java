package com.example.service;

import com.example.dto.CartReceive;
import com.example.dto.ResponseResult;

public interface CartService {

    ResponseResult createCart(CartReceive cartReceive);

    ResponseResult updateCart(CartReceive cartReceive);
}
