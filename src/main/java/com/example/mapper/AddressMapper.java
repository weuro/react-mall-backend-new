package com.example.mapper;

import com.example.model.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Select("SELECT * FROM address WHERE userId = #{userId}")
    List<Address> getAddress(Long userId);
}
