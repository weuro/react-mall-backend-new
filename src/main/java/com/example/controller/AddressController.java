package com.example.controller;

import com.example.dto.ResponseResult;
import com.example.model.Address;
import com.example.service.AddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Resource
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseResult addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @PutMapping("/update")
    public ResponseResult updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/delete")
    public ResponseResult deleteAddress(@RequestParam Long id, @RequestParam Long userId) {
        return addressService.deleteAddress(id, userId);
    }

    @GetMapping("/list")
    public ResponseResult getAddressesByUserId(@RequestParam Long userId) {
        return addressService.getAddressesByUserId(userId);
    }
}
