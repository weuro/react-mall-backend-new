package com.example.controller;

import com.example.dto.ResponseResult;
import com.example.service.ItemService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/api/item")
    public ResponseResult getItemsByCategory(@RequestBody Map<String, Object> map,
                                             @RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "20") int size) {
        String category = (String) map.get("category");
        return itemService.getItemsByCategory(category, page, size);
    }
    @GetMapping("/api/item/categoryCounts")
    public ResponseResult getCategoryCounts() {
        return itemService.getCategoryCounts();
    }

    @GetMapping("/api/item/category/filter")
    public ResponseResult getItemsByCategoryAndFilters(
            @RequestParam String category,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) String brand) {
        return itemService.getItemsByCategoryAndFilters(category, minPrice, maxPrice, brand);
    }

    @GetMapping("/api/item/filter")
    public ResponseResult getItemsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) String brand) {
        return itemService.getItemsByFilters(name, minPrice, maxPrice, brand);
    }
}
