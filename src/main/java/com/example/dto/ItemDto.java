package com.example.dto;

import lombok.Data;

import java.sql.Timestamp;

// 主要用于进行分类操作，已经有的ItemSends内无类别，所以需要新建dto
@Data
public class ItemDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;
    private String image;
    private String category;
    private String brand;
    private String spec;
    private Integer sold;
    private Integer commentCount;
    private Boolean isAD;
    private Integer status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long creater;
    private Long updater;
}
