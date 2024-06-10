package com.example.service;

import com.example.dto.CartReceive;
import com.example.dto.OrderCreate;
import com.example.dto.OrderPush;
import com.example.dto.ResponseResult;
import com.example.model.Order;

import java.util.List;

public interface OrderService {

    ResponseResult createOrder(OrderCreate orderCreate);

    ResponseResult getOrderById(Long id);

    ResponseResult payOrder(OrderCreate orderCreate);

    ResponseResult upPayOrder(Long id);

    ResponseResult pushOrder(OrderPush orderPush);

    List<Order> getAllOrders();


    ResponseResult cancelOrder(Long id);

    boolean shipOrder(Long id);

    List<Order> getOrdersByUserId(Long userId);

    boolean deleteOrder(Long id);

}
