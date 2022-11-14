package com.common.opthelpersever.service.impl;

import com.common.opthelpersever.entity.FoodList;
import com.common.opthelpersever.mapper.FoodChooseMapper;
import com.common.opthelpersever.service.FoodChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物选择实现类
 **/
@Service
public class FoodChooseServiceImpl implements FoodChooseService {
    @Autowired
    private FoodChooseMapper foodChooseMapper;
    @Override
    public List<FoodList> queryFoodList() {
        return foodChooseMapper.queryFoodList();
    }
}
