package com.example.mapper;

import com.example.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}
