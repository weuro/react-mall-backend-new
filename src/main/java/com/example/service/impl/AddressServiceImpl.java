package com.example.service.impl;

import com.example.dto.ResponseResult;
import com.example.mapper.AddressMapper;
import com.example.model.Address;
import com.example.service.AddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;

    @Override
    public ResponseResult addAddress(Address address) {
        addressMapper.insertAddress(address);
        return new ResponseResult(200, "地址添加成功", address);
    }

    @Override
    public ResponseResult updateAddress(Address address) {
        addressMapper.updateAddress(address);
        return new ResponseResult(200, "地址更新成功", address);
    }

    @Override
    public ResponseResult deleteAddress(Long id, Long userId) {
        addressMapper.deleteAddress(id, userId);
        return new ResponseResult(200, "地址删除成功");
    }

    @Override
    public ResponseResult getAddressesByUserId(Long userId) {
        List<Address> addresses = addressMapper.selectAddressesByUserId(userId);
        return new ResponseResult(200, "查询成功", addresses);
    }

    @Override
    public ResponseResult getAddress(Long userId) {
        List<Address> addresses = addressMapper.getAddress(userId);

        if (addresses != null) {
            return new ResponseResult(200, "success", addresses);
        }
        return new ResponseResult(400, "failed", null);
    }
}

