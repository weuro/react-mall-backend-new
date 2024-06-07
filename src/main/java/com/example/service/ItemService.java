package com.example.service;

import com.example.dto.ResponseResult;

public interface ItemService {
    ResponseResult get50Items();

    ResponseResult getDetail(Long id);
}
