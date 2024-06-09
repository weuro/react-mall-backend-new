package com.example.mapper;

import com.example.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO `order` " +
            "(id, total_fee, payment_type, user_id, status, address_id) " +
            "VALUES (#{id}, #{total_fee}, #{payment_type}, #{user_id}, #{status}, #{address_id})")
    int insertOrder(Order order);


}
