package com.example.model;

import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Cart implements Serializable {

    private Long id;
    private Long userId;
    private Long itemId;
    private Integer num;
    private String name;
    private String spec;
    private Integer price;
    private String image;
    private Timestamp createTime;
    private Timestamp updateTime;
}
