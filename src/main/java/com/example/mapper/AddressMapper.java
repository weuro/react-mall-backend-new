package com.example.mapper;

import com.example.model.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {
    @Insert("INSERT INTO address (userId, province, city, town, mobile, street, contact, isDefault, notes) " +
            "VALUES (#{userId}, #{province}, #{city}, #{town}, #{mobile}, #{street}, #{contact}, #{isDefault}, #{notes})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertAddress(Address address);

    @Update("UPDATE address SET province = #{province}, city = #{city}, town = #{town}, mobile = #{mobile}, " +
            "street = #{street}, contact = #{contact}, isDefault = #{isDefault}, notes = #{notes} " +
            "WHERE id = #{id} AND userId = #{userId}")
    void updateAddress(Address address);

    @Delete("DELETE FROM address WHERE id = #{id} AND userId = #{userId}")
    void deleteAddress(@Param("id") Long id, @Param("userId") Long userId);

    @Select("SELECT * FROM address WHERE userId = #{userId}")
    List<Address> selectAddressesByUserId(@Param("userId") Long userId);

    @Select("SELECT * FROM address WHERE userId = #{userId}")
    List<Address> getAddress(Long userId);

    @Update("UPDATE address SET isDefault = '0' WHERE userId = #{userId}")
    void resetDefaultAddress(@Param("userId") Long userId);

    @Update("UPDATE address SET isDefault = '1' WHERE id = #{id} AND userId = #{userId}")
    void setDefaultAddress(@Param("id") Long id, @Param("userId") Long userId);
}

