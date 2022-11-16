package com.common.opthelpersever.mapper;

import com.common.opthelpersever.entity.FoodList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description ...
 **/
@Mapper
public interface FoodChooseMapper {
    List<FoodList> queryFoodList();

    int addFoodList(FoodList foodList);

    List<FoodList> queryListByName(FoodList foodList);
}