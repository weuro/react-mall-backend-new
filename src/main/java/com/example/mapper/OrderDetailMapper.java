package com.example.mapper;

import com.example.model.Cart;
import com.example.model.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    int insertOrderDetail(@Param("carts") List<Cart> carts, @Param("order_id") Long order_id);

    @Select("select * from order_detail where order_id = #{order_id}")
    List<OrderDetail> findItemIdsByOrderId(Long order_id);

    @Insert("insert into order_detail (order_id, item_id, num, name, spec, price, image)" +
            " values (#{orderDetail.order_id}, #{orderDetail.item_id}, #{orderDetail.num}, #{orderDetail.name}, #{orderDetail.spec}, #{orderDetail.price}, #{orderDetail.image})")
    int insertOrderDetailByOrderDetail(@Param("orderDetail") OrderDetail orderDetail);
}
