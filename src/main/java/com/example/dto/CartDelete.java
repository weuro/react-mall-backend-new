package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDelete {
    private List<Long> ids;

    // Getter 和 Setter 方法
    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
