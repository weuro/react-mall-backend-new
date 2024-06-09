package com.example.service;

import com.example.dto.CartDelete;
import com.example.dto.CartReceive;
import com.example.dto.ResponseResult;

public interface CartService {

    ResponseResult createCart(CartReceive cartReceive);

    ResponseResult updateCart(CartReceive cartReceive);

    ResponseResult listCart(Long user_id);

    ResponseResult deleteCart(Long id);

    ResponseResult deleteAllCart(CartDelete cartDelete);
}
