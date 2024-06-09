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
    public ResponseResult getAddress(Long userId) {
        List<Address> addresses = addressMapper.getAddress(userId);

        if (addresses != null) {
            return new ResponseResult(200, "success", addresses);
        }
        return new ResponseResult(400, "failed", null);
    }
}
