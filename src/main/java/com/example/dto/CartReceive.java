package com.example.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CartReceive {
    private Long id;
    private Long user_id;
    private Long item_id;
    private int num;
}
