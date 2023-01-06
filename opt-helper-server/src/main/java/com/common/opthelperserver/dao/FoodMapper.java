package com.common.opthelperserver.dao;

import com.common.opthelperserver.entity.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description ...
 **/
@Mapper
public interface FoodMapper {
    /**
     * 查询所有
     * @return foodList
     */
    List<Food> queryFoodList();

    /**
     * 新增
     * @param food
     * @return
     */
    int addFoodList(Food food);

    /**
     * 通过名称查询
     * @param food
     * @return
     */
    List<Food> queryListByName(Food food);

    /**
     * 通过id查询
     * @param food
     * @return
     */
    List<Food> queryListById(Food food);

    /**
     * 修改
     * @param food
     * @return
     */
    int updateFoodList(Food food);

    /**
     * 删除
     * @param food
     * @return
     */
    int deleteFoodList(Food food);
}
