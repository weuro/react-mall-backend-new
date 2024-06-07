package com.example.service.impl;

import com.example.dto.CartReceive;
import com.example.dto.ResponseResult;
import com.example.mapper.CartMapper;
import com.example.mapper.ItemMapper;
import com.example.model.Cart;
import com.example.model.Item;
import com.example.service.CartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private CartMapper cartMapper;

    @Override
    public ResponseResult createCart(CartReceive cartReceive) {
        Item item = itemMapper.selectItemById(cartReceive.getItem_id());

        Cart cart = new Cart()
                .setUser_id(cartReceive.getUser_id())
                .setItem_id(cartReceive.getItem_id())
                .setNum(cartReceive.getNum())
                .setName(item.getName())
                .setSpec(item.getSpec())
                .setPrice(item.getPrice())
                .setImage(item.getImage());

        int res = cartMapper.insertCart(cart);

        if (res == 1) {
            return new ResponseResult(200, "success", null);
        } else {
            return new ResponseResult(500, "failed", null);
        }
    }

    @Override
    public ResponseResult updateCart(CartReceive cartReceive) {
        int res = cartMapper.updateCartById(cartReceive.getNum(), cartReceive.getId());

        if (res == 1) {
            return new ResponseResult(200, "success", null);
        } else {
            return new ResponseResult(500, "failed", null);
        }
    }
}
