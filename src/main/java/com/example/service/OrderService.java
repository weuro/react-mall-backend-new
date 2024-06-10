package com.example.service;

import com.example.dto.CartReceive;
import com.example.dto.OrderCreate;
import com.example.dto.OrderPush;
import com.example.dto.ResponseResult;

public interface OrderService {

    ResponseResult createOrder(OrderCreate orderCreate);

    ResponseResult getOrderById(Long id);

    ResponseResult payOrder(OrderCreate orderCreate);

    ResponseResult upPayOrder(Long id);

    ResponseResult pushOrder(OrderPush orderPush);
}
