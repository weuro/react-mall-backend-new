package com.example.service;

import com.example.dto.ResponseResult;

public interface ItemService {
    ResponseResult get50Items();

    ResponseResult getDetail(Long id);

    ResponseResult getItemsByCategory(String category, int page, int size);

    ResponseResult getCategoryCounts();

    ResponseResult getItemsByCategoryAndFilters(String category, Integer minPrice, Integer maxPrice, String brand, int page, int size);
    ResponseResult getItemsByFilters(String name, Integer minPrice, Integer maxPrice, String brand, int page, int size);
}
