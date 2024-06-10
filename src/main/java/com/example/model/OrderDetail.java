
package com.example.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class OrderDetail implements Serializable {

    private Long id;
    private Long order_id;
    private Long item_id;
    private Integer num;
    private String name;
    private String spec;
    private Integer price;
    private String image;
    private Timestamp create_time;
    private Timestamp update_time;
}
