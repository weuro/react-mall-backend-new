package com.example.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class Order implements Serializable {

    private Long id;
    private Integer total_fee;
    private Integer payment_type;
    private Long user_id;
    private Integer status;
    private Timestamp create_time;
    private Timestamp pay_time;
    private Timestamp consign_time;
    private Timestamp end_time;
    private Timestamp close_time;
    private Timestamp comment_time;
    private Timestamp update_time;
    private Long address_id;
}
