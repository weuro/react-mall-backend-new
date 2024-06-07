package com.example.mapper;

import com.example.dto.ItemSend;
import com.example.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {
    @Select("SELECT * FROM item WHERE id = #{id}")
    Item selectItemById(Long id);
//
//    @Select("SELECT COUNT(*) FROM item")
//    int getItemsCount();

    @Select("SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY RAND()) AS rn FROM item) AS t WHERE rn <= 50")
    List<ItemSend> selectRandomItems();
}
