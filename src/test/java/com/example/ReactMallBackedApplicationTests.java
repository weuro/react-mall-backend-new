package com.example;

import com.example.mapper.ItemMapper;
import com.example.mapper.OrderMapper;
import com.example.model.Item;
import com.example.model.Order;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.UUID;

@SpringBootTest
class ReactMallBackedApplicationTests {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ItemMapper itemMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void insertOrder() {
        long currentTimeMillis = System.currentTimeMillis();
        int randomValue = new Random().nextInt(1000);
        Long id = currentTimeMillis * 1000 + randomValue;
        Order order = new Order()
                .setId(id)
                .setTotal_fee(1234)
                .setAddress_id(59L)
                .setPayment_type(003)
                .setUser_id(1L)
                .setStatus(0);

        int res = orderMapper.insertOrder(order);
        System.out.println(res);
    }

    @Test
    void updateItemStock() {
        Item item = itemMapper.selectItemById(317578L);
        int res = itemMapper.updateItemStock(item.getId(), item.getStock() - 1);
        System.out.println(res);
    }

}
