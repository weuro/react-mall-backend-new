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

    @PostMapping("/api/item/category/filter")
    public ResponseResult getItemsByCategoryAndFilters(@RequestBody Map<String, Object> map) {
        String category = (String) map.get("category");
        Integer minPrice = (Integer) map.get("minPrice");
        Integer maxPrice = (Integer) map.get("maxPrice");
        String brand = (String) map.get("brand");
        int page = (int) map.getOrDefault("page", 1);
        int size = (int) map.getOrDefault("size", 20);
        return itemService.getItemsByCategoryAndFilters(category, minPrice, maxPrice, brand, page, size);
    }

    @PostMapping("/api/item/filter")
    public ResponseResult getItemsByFilters(@RequestBody Map<String, Object> map) {
        String name = (String) map.get("name");
        Integer minPrice = (Integer) map.get("minPrice");
        Integer maxPrice = (Integer) map.get("maxPrice");
        String brand = (String) map.get("brand");
        int page = (int) map.getOrDefault("page", 1);
        int size = (int) map.getOrDefault("size", 20);
        return itemService.getItemsByFilters(name, minPrice, maxPrice, brand, page, size);
    }
}
