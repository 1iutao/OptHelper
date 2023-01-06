package com.common.opthelperserver.service;

import com.common.opthelperserver.entity.Food;

import java.util.List;
import java.util.Map;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物选择服务类
 **/
public interface FoodService {

    /**
     * 查询所有
     * @return foodList
     */
    List<Food> queryFoodList();

    /**
     * 随机查询n个
     * @param n
     * @return foodList
     */
    public List<Food> queryRandomFoodList(Integer n);

    /**
     * 新增
     * @param params
     * @return int
     */
    int addFoodList(Map<String, String> params);

    /**
     * 修改
     * @param params
     * @return int
     */
    int updateFoodList(Map<String, String> params);

    /**
     * 删除
     * @param params
     * @return int
     */
    int deleteFoodList(Map<String, String> params);
}
