package com.example.dto;

import lombok.Data;

@Data
public class ItemResponse {
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;
    private String image;
    private String brand;
}
