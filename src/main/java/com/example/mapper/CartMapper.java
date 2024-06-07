package com.example.mapper;

import com.example.model.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CartMapper {

    @Insert("INSERT INTO cart (user_id, item_id, num, name, spec, price, image) VALUES (#{user_id}, #{item_id}, #{num}, #{name}, #{spec}, #{price}, #{image})")
    int insertCart(Cart cart);

    @Update("UPDATE cart SET num = #{num} WHERE id = #{id}")
    int updateCartById(int num, Long id);
}
