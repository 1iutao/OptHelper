package com.common.opthelperserver.service;

import com.common.opthelperserver.entity.FoodList;

import java.util.List;
import java.util.Map;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物选择服务类
 **/
public interface FoodChooseService {
    List<FoodList> queryFoodList();

    int addFoodList(Map<String, String> params);
}
