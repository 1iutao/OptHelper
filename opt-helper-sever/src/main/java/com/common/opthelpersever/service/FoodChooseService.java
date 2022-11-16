package com.common.opthelpersever.service;

import com.common.opthelpersever.entity.FoodList;

import java.util.List;
import java.util.Map;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物选择服务类
 **/
public interface FoodChooseService {

    // 查询所有
    List<FoodList> queryFoodList();

    // 随机查询n个
    public List<FoodList> queryRandomFoodList(int n);

    int addFoodList(Map<String, String> params);
}
