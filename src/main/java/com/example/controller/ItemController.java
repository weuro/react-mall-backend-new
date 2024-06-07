package com.example.controller;

import com.example.dto.ResponseResult;
import com.example.service.ItemService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Resource
    private ItemService itemService;

    @GetMapping("/api/item/get50")
    public ResponseResult get50Items() {
        return itemService.get50Items();
    }

    @GetMapping("/api/item/detail")
    public ResponseResult getDetail(@RequestParam Long id) {
        return itemService.getDetail(id);
    }
}
