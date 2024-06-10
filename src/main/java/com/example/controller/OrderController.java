package com.example.controller;

import com.example.dto.*;
import com.example.model.Order;
import com.example.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/api/order/create")
    public ResponseResult createOrder(@RequestBody OrderCreate orderCreate) {
        return orderService.createOrder(orderCreate);
    }

    @PostMapping("/api/order/push")
    public ResponseResult pushOrder(@RequestBody OrderPush orderPush) {
        return orderService.pushOrder(orderPush);
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

    @GetMapping("/api/order/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/api/order/cancel")
    public ResponseResult cancelOrder(@RequestParam Long id) {
        return orderService.cancelOrder(id);
    }
    @PutMapping("/api/order/ship")
    public String shipOrder(@RequestParam Long id) {
        boolean success = orderService.shipOrder(id);
        if (success) {
            return "Order shipped successfully.";
        } else {
            return "Order shipping failed. The order might not be in the correct status.";
        }
    }

    @GetMapping("/api/order/user")
    public ResponseResult<List<Order>> getOrdersByUserId(@RequestParam Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return new ResponseResult<>(200, orders);
    }

    @DeleteMapping("/api/order/delete")
    public ResponseResult<String> deleteOrder(@RequestParam Long id) {
        boolean success = orderService.deleteOrder(id);
        if (success) {
            return new ResponseResult<>(200, "Order deleted successfully.");
        } else {
            return new ResponseResult<>(400, "Order deletion failed. The order might not exist.");
        }

    }

    @GetMapping("/api/order/user/ordersWithDetails")
    public ResponseResult<List<OrderDTO>> getOrdersWithDetailsByUserId(@RequestParam Long userId) {
        List<OrderDTO> orders = orderService.getOrdersWithDetailsByUserId(userId);
        return new ResponseResult<>(200, orders);
    }

}
