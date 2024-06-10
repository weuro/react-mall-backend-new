package com.example.service.impl;

import com.example.dto.CartReceive;
import com.example.dto.OrderCreate;
import com.example.dto.OrderPush;
import com.example.dto.ResponseResult;
import com.example.mapper.*;
import com.example.model.*;
import com.example.service.OrderService;
import com.example.util.LogUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    @Resource
    private UserMapper userMapper;

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
                .setStatus(1);

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
    @Transactional
    public ResponseResult pushOrder(OrderPush orderPush) {
        Item item = itemMapper.selectItemById(orderPush.getItem_id());

        if (item.getStock() < orderPush.getNum()) {
            throw new RuntimeException("库存不足");
        }

        int total_fee = item.getPrice() * orderPush.getNum();

        long currentTimeMillis = System.currentTimeMillis();
        int randomValue = new Random().nextInt(1000);
        Long id = currentTimeMillis * 1000 + randomValue;
        Order order = new Order()
                .setId(id)
                .setTotal_fee(total_fee)
                .setAddress_id(orderPush.getAddress_id())
                .setPayment_type(003)
                .setUser_id(orderPush.getUser_id())
                .setStatus(1);

        int res = orderMapper.insertOrder(order);
        if (res != 1) {
            throw new RuntimeException("订单创建失败");
        }

        OrderDetail orderDetail = new OrderDetail()
                .setOrder_id(id)
                .setItem_id(orderPush.getItem_id())
                .setNum(orderPush.getNum())
                .setName(item.getName())
                .setSpec(item.getSpec())
                .setPrice(item.getPrice())
                .setImage(item.getImage());

        int res2 = orderDetailMapper.insertOrderDetailByOrderDetail(orderDetail);
        if (res2 != 1) {
            throw new RuntimeException("订单详情创建失败");
        }
        return new ResponseResult(200, "订单创建成功", Collections.singletonMap("order_id", id));
    }

    @Override
    public ResponseResult getOrderById(Long id) {
        Order order = orderMapper.selectOrderById(id);

        if (order != null) {
            return new ResponseResult(200, "查询成功", order);
        }
        return new ResponseResult(400, "查询失败");
    }

    @Transactional
    @Override
    public ResponseResult payOrder(OrderCreate orderCreate) {
        Long id = orderCreate.getId();

        List<Long> cart_ids = orderCreate.getCart_ids();

        if ( !(cart_ids == null || cart_ids.isEmpty())) {
            // 删除购物车
            int res = cartMapper.deleteAllCartByIds(cart_ids);
            if (res != cart_ids.size()) {
                throw new RuntimeException("删除购物车失败");
            }
        }

        // 查询订单
        Order order = orderMapper.selectOrderById(id);
        if (order == null) {
            return new ResponseResult(400, "订单不存在");
        }

        List<OrderDetail> orderDetails = orderDetailMapper.findItemIdsByOrderId(id);
        if (orderDetails.isEmpty()) {
            return new ResponseResult(400, "订单详情不存在");
        }

        Item item = null;
        for (OrderDetail orderDetail : orderDetails) {
            // 根据item_id查找商品信息
            item = itemMapper.selectItemById(orderDetail.getItem_id());
            if (item == null || item.getStock() < orderDetail.getNum()) {
                throw new RuntimeException("库存不足或商品不存在");
            }
            int updateStockResult = itemMapper.updateItemStock(item.getId(), orderDetail.getNum());
            if (updateStockResult != 1) {
                throw new RuntimeException("更新库存失败");
            }
        }

        // 更新余额
        User user = userMapper.findUserById(order.getUser_id());
        if (user == null || user.getBalance() < order.getTotal_fee()) {
            throw new RuntimeException("余额不足或用户不存在");
        }

        int updateBalanceResult = userMapper.updateBalance(order.getUser_id(), order.getTotal_fee());
        if (updateBalanceResult != 1) {
            throw new RuntimeException("更新余额失败");
        }

        // 更新状态
        int updateOrderResult = orderMapper.updateOrderStatus(id, 2);
        if (updateOrderResult != 1) {
            throw new RuntimeException("更新订单状态失败");
        }

        return new ResponseResult(200, "支付成功");
    }


    @Override
    public ResponseResult upPayOrder(Long id) {
        int res = orderMapper.updateOrderStatus(id, 5);

        if (res == 1) {
            return new ResponseResult(200, "超时未支付");
        }
        return new ResponseResult(400, "处理失败");
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.selectAllOrders();
    }

}
