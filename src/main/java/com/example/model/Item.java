package com.example.model;

import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Item implements Serializable {

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
