package com.common.opthelpersever.service;

import com.common.opthelpersever.entity.FoodList;

import java.util.List;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物选择服务类
 **/
public interface FoodChooseService {
    List<FoodList> queryFoodList();
}
