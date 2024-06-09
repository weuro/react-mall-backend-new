package com.example.controller;

import com.example.dto.ResponseResult;
import com.example.service.AddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Resource
    private AddressService addressService;

    @GetMapping("/api/address/get")
    public ResponseResult getAddress(@RequestParam Long userId) {
        return addressService.getAddress(userId);
    }
}
