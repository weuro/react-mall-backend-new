package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreate {
    private Long id;
    private List<Long> cart_ids;
    private Long address_id;
}
