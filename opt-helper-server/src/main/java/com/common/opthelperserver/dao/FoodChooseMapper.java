package com.common.opthelperserver.dao;

import com.common.opthelperserver.entity.FoodList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description ...
 **/
@Mapper
public interface FoodChooseMapper {
    /**
     * 查询所有
     * @return foodList
     */
    List<FoodList> queryFoodList();

    /**
     * 新增
     * @param foodList
     * @return
     */
    int addFoodList(FoodList foodList);

    /**
     * 通过名称查询
     * @param foodList
     * @return
     */
    List<FoodList> queryListByName(FoodList foodList);

    /**
     * 通过id查询
     * @param foodList
     * @return
     */
    List<FoodList> queryListById(FoodList foodList);

    /**
     * 修改
     * @param foodList
     * @return
     */
    int updateFoodList(FoodList foodList);

    /**
     * 删除
     * @param foodList
     * @return
     */
    int deleteFoodList(FoodList foodList);
}
