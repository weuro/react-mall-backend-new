package com.example.model;

import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Order implements Serializable {

    private Long id;
    private Integer totalFee;
    private Integer paymentType;
    private Long userId;
    private Integer status;
    private Timestamp createTime;
    private Timestamp payTime;
    private Timestamp consignTime;
    private Timestamp endTime;
    private Timestamp closeTime;
    private Timestamp commentTime;
    private Timestamp updateTime;
}
