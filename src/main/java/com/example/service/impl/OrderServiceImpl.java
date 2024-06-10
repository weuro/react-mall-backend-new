package com.example.service.impl;

import com.example.dto.OrderCreate;
import com.example.dto.ResponseResult;
import com.example.mapper.CartMapper;
import com.example.mapper.ItemMapper;
import com.example.mapper.OrderDetailMapper;
import com.example.mapper.OrderMapper;
import com.example.model.Cart;
import com.example.model.Item;
import com.example.model.Order;
import com.example.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private ItemMapper itemMapper;

    @Override
    public ResponseResult createOrder(OrderCreate orderCreate) {
        List<Cart> carts = cartMapper.findCartByIds(orderCreate.getCart_ids());

        if (carts == null || carts.size() == 0) {
            return new ResponseResult(400, "购物车为空");
        }

        Item item = null;
        for (Cart cart : carts) {
            // 根据item_id查找商品信息
            item = itemMapper.selectItemById(cart.getItem_id());
            if (item.getStock() < cart.getNum()) {
                return new ResponseResult(400, "库存不足");
            }
        }

        int total_fee = 0;
        for (Cart cart : carts) {
            total_fee += cart.getPrice() * cart.getNum();
        }

        long currentTimeMillis = System.currentTimeMillis();
        int randomValue = new Random().nextInt(1000);
        Long id = currentTimeMillis * 1000 + randomValue;
        Order order = new Order()
                .setId(id)
                .setTotal_fee(total_fee)
                .setAddress_id(orderCreate.getAddress_id())
                .setPayment_type(003)
                .setUser_id(carts.get(0).getUser_id())
                .setStatus(0);

        int res = orderMapper.insertOrder(order);

        if (res != 1) {
            return new ResponseResult(400, "订单创建失败");
        }

        int res2 = orderDetailMapper.insertOrderDetail(carts, id);

        if (res2 != carts.size()) {
            return new ResponseResult(400, "订单详情创建失败");
        }

        Map<String, Long> data = Map.of("order_id", id);
        return new ResponseResult(200, "订单创建成功", data);
    }

    @Override
    public ResponseResult getOrderById(Long id) {
        Order order = orderMapper.selectOrderById(id);

        if (order != null) {
            return new ResponseResult(200, "查询成功", order);
        }
        return new ResponseResult(400, "查询失败");
    }

    @Override
    public ResponseResult payOrder(Long id) {
        int res = orderMapper.updateOrderStatus(id, 1);

        if (res == 1) {
            return new ResponseResult(200, "支付成功");
        }
        return new ResponseResult(400, "支付失败");
    }

    @Override
    public ResponseResult upPayOrder(Long id) {
        int res = orderMapper.updateOrderStatus(id, 2);

        if (res == 1) {
            return new ResponseResult(200, "超时未支付");
        }
        return new ResponseResult(400, "处理失败");
    }
}
