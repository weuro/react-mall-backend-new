package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    User findUserByPhone(String phone);

    int insertUser(User user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findUserById(Long id);

    @Update("UPDATE user SET balance = balance - #{total_fee} WHERE id = #{user_id}")
    int updateBalance(Long user_id, int total_fee);
}
