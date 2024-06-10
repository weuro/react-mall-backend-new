package com.example.dto;

import com.example.model.Order;
import com.example.model.OrderDetail;
import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private Order order;
    private List<OrderDetail> orderDetails;
}
