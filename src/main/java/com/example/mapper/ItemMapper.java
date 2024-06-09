package com.example.mapper;

import com.example.dto.CategoryCount;
import com.example.dto.ItemResponse;
import com.example.dto.ItemSend;
import com.example.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    // 根据类别查询

    @Select("SELECT id, name, price, stock, image, brand FROM item WHERE category = #{category}")
    List<ItemResponse> selectItemsByCategory(@Param("category") String category);

    // 获得类别和对应数量
    @Select("SELECT category, COUNT(*) as count FROM item GROUP BY category")
    List<CategoryCount> selectCategoryCounts();

    // 动态SQL
    @Select("<script>" +
            "SELECT * FROM item WHERE category = #{category}" +
            "<if test='minPrice != null'> AND price &gt;= #{minPrice}</if>" +
            "<if test='maxPrice != null'> AND price &lt;= #{maxPrice}</if>" +
            "<if test='brand != null'> AND brand = #{brand}</if>" +
            "</script>")
    List<ItemSend> selectItemsByCategoryAndFilters(@Param("category") String category,
                                                   @Param("minPrice") Integer minPrice,
                                                   @Param("maxPrice") Integer maxPrice,
                                                   @Param("brand") String brand);

    @Select("<script>" +
            "SELECT * FROM item WHERE 1=1" +
            "<if test='name != null'> AND name LIKE CONCAT('%', #{name}, '%')</if>" +
            "<if test='minPrice != null'> AND price &gt;= #{minPrice}</if>" +
            "<if test='maxPrice != null'> AND price &lt;= #{maxPrice}</if>" +
            "<if test='brand != null'> AND brand LIKE CONCAT('%', #{brand}, '%')</if>" +
            "</script>")
    List<ItemSend> selectItemsByFilters(@Param("name") String name,
                                        @Param("minPrice") Integer minPrice,
                                        @Param("maxPrice") Integer maxPrice,
                                        @Param("brand") String brand);
}
