package com.example.mapper;

import com.example.model.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    int insertOrderDetail(@Param("carts") List<Cart> carts, @Param("order_id") Long order_id);
}
