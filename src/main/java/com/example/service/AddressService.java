package com.example.service;

import com.example.dto.ResponseResult;
import com.example.model.Address;

import java.util.List;

public interface AddressService {

    void setDefaultAddress(Long userId, Long addressId);
    ResponseResult addAddress(Address address);
    ResponseResult updateAddress(Address address);
    ResponseResult deleteAddress(Long id, Long userId);
    ResponseResult getAddressesByUserId(Long userId);
    ResponseResult getAddress(Long userId);
}

