package com.example.service.impl;

import com.example.dto.CategoryCount;
import com.example.dto.ItemResponse;
import com.example.dto.ItemSend;
import com.example.dto.ResponseResult;
import com.example.mapper.ItemMapper;
import com.example.model.Item;
import com.example.service.ItemService;
import com.example.util.LogUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemMapper itemMapper;

    @Override
    public ResponseResult get50Items() {
        List<ItemSend> itemSends = itemMapper.selectRandomItems();
        LogUtil.log(itemSends.size() + "");
        return new ResponseResult(200, "随机查询50条数据", itemSends);
    }

    @Override
    public ResponseResult getDetail(Long id) {
        Item item = itemMapper.selectItemById(id);
        LogUtil.log(item.toString());
        return new ResponseResult(200, "查询成功", item);
    }

    @Override
    public ResponseResult getItemsByCategory(String category, int page, int size) {
        int offset = (page - 1) * size;
        List<ItemResponse> itemResponses = itemMapper.selectItemsByCategoryWithPagination(category, size, offset);
        LogUtil.log(itemResponses.size() + "");
        return new ResponseResult(200, "查询成功", itemResponses);
    }

    @Override
    public ResponseResult getCategoryCounts() {
        List<CategoryCount> categoryCounts = itemMapper.selectCategoryCounts();
        LogUtil.log(categoryCounts.toString());
        return new ResponseResult(200, "查询成功", categoryCounts);
    }

    @Override
    public ResponseResult getItemsByCategoryAndFilters(String category, Integer minPrice, Integer maxPrice, String brand, int page, int size) {
        int offset = (page - 1) * size;
        List<ItemResponse> itemResponses = itemMapper.selectItemsByCategoryAndFilters(category, minPrice, maxPrice, brand, size, offset);
        LogUtil.log(itemResponses.size() + "");
        return new ResponseResult(200, "查询成功", itemResponses);
    }

    @Override
    public ResponseResult getItemsByFilters(String name, Integer minPrice, Integer maxPrice, String brand, int page, int size) {
        int offset = (page - 1) * size;
        List<ItemResponse> itemResponses = itemMapper.selectItemsByFilters(name, minPrice, maxPrice, brand, size, offset);
        LogUtil.log(itemResponses.size() + "");
        return new ResponseResult(200, "查询成功", itemResponses);
    }
}
