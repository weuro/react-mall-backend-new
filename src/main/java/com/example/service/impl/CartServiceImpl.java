package com.example.service.impl;

import com.example.dto.CartDelete;
import com.example.dto.CartReceive;
import com.example.dto.ResponseResult;
import com.example.mapper.CartMapper;
import com.example.mapper.ItemMapper;
import com.example.model.Cart;
import com.example.model.Item;
import com.example.service.CartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private CartMapper cartMapper;

    @Override
    public ResponseResult createCart(CartReceive cartReceive) {
        Cart cartExist = cartMapper.selectCartById(cartReceive.getUser_id(), cartReceive.getItem_id());

        if (cartExist != null) {
            cartReceive.setId(cartExist.getId())
                    .setNum(cartExist.getNum() + cartReceive.getNum());

            return updateCart(cartReceive);
        }

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
            return new ResponseResult(200, "success");
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

    @Override
    public ResponseResult listCart(Long user_id) {
        List<Cart> carts = cartMapper.selectCartByUserId(user_id);

        if (carts != null) {
            return new ResponseResult(200, "success", carts);
        }

        return new ResponseResult(500, "failed", null);
    }

    @Override
    public ResponseResult deleteCart(Long id) {
        int res = cartMapper.deleteCartById(id);

        if (res == 1) {
            return new ResponseResult(200, "success", null);
        } else {
            return new ResponseResult(500, "failed", null);
        }
    }

    @Override
    public ResponseResult deleteAllCart(CartDelete cartDelete) {
        List<Long> ids = cartDelete.getIds();

        if (Objects.isNull(ids) || ids.isEmpty()) {
            return new ResponseResult(500, "failed", null);
        }

        int res = cartMapper.deleteAllCartByIds(ids);

        if (res == ids.size()) {
            return new ResponseResult(200, "success", null);
        } else {
            return new ResponseResult(500, "failed", null);
        }
    }

    @Override
    public ResponseResult findCart(CartDelete cartDelete) {
        List<Long> ids = cartDelete.getIds();

        if (Objects.isNull(ids) || ids.isEmpty()) {
            return new ResponseResult(500, "failed", null);
        }

        List<Cart> carts = cartMapper.findCartByIds(ids);

        if (carts != null) {
            return new ResponseResult(200, "success", carts);
        }
        return  new ResponseResult(500, "failed", null);
    }
}
