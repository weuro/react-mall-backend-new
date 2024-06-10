package com.example.mapper;

import com.example.dto.OrderDTO;
import com.example.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO `order` " +
            "(id, total_fee, payment_type, user_id, status, address_id) " +
            "VALUES (#{id}, #{total_fee}, #{payment_type}, #{user_id}, #{status}, #{address_id})")
    int insertOrder(Order order);

    @Select("SELECT * FROM `order` WHERE id = #{id} FOR UPDATE")
    Order selectOrderById(Long id);

    @Update("UPDATE `order` SET status = #{status} WHERE id = #{id}")
    int updateOrderStatus(Long id, int status);

    @Select("SELECT * FROM `order`")
    List<Order> selectAllOrders();

    @Update("UPDATE `order` SET status = 3 WHERE id = #{id} AND status = 2")
    int shipOrder(Long id);

    @Select("SELECT * FROM `order` WHERE user_id = #{userId}")
    List<Order> selectOrdersByUserId(Long userId);

    @Delete("DELETE FROM `order` WHERE id = #{id}")
    int deleteOrder(Long id);

    @Select("SELECT o.*, d.* " +
            "FROM `order` o " +
            "LEFT JOIN `order_detail` d ON o.id = d.order_id " +
            "WHERE o.user_id = #{userId}")
    @Results({
            @Result(property = "order.id", column = "id"),
            @Result(property = "order.total_fee", column = "total_fee"),
            @Result(property = "order.payment_type", column = "payment_type"),
            @Result(property = "order.user_id", column = "user_id"),
            @Result(property = "order.status", column = "status"),
            @Result(property = "order.create_time", column = "create_time"),
            @Result(property = "order.pay_time", column = "pay_time"),
            @Result(property = "order.consign_time", column = "consign_time"),
            @Result(property = "order.end_time", column = "end_time"),
            @Result(property = "order.close_time", column = "close_time"),
            @Result(property = "order.comment_time", column = "comment_time"),
            @Result(property = "order.update_time", column = "update_time"),
            @Result(property = "order.address_id", column = "address_id"),
            @Result(property = "orderDetails", column = "id",
                    many = @Many(select = "com.example.mapper.OrderDetailMapper.selectOrderDetailsByOrderId"))
    })
    List<OrderDTO> selectOrdersWithDetailsByUserId(Long userId);

}
