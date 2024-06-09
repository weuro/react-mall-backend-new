package com.example.mapper;

import com.example.model.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Insert("INSERT INTO cart (user_id, item_id, num, name, spec, price, image) VALUES (#{user_id}, #{item_id}, #{num}, #{name}, #{spec}, #{price}, #{image})")
    int insertCart(Cart cart);

    @Update("UPDATE cart SET num = #{num} WHERE id = #{id}")
    int updateCartById(int num, Long id);

    @Select("SELECT * FROM cart WHERE user_id = #{user_id} AND item_id = #{item_id}")
    Cart selectCartById(Long user_id, Long item_id);

    @Select("SELECT * FROM cart WHERE user_id = #{user_id}")
    List<Cart> selectCartByUserId(Long user_id);

    @Delete("DELETE FROM cart WHERE id = #{id}")
    int deleteCartById(Long id);

    int deleteAllCartByIds(List<Long> ids);

    List<Cart> findCartByIds(List<Long> ids);
}
