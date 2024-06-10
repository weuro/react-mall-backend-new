package com.example.mapper;

import com.example.model.Cart;
import com.example.model.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    int insertOrderDetail(@Param("carts") List<Cart> carts, @Param("order_id") Long order_id);

    @Select("select * from order_detail where order_id = #{order_id}")
    List<OrderDetail> findItemIdsByOrderId(Long order_id);
}
