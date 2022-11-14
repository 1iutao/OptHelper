package com.common.opthelpersever.service.impl;

import com.common.opthelpersever.config.DateConfig;
import com.common.opthelpersever.config.SeverError;
import com.common.opthelpersever.config.SeverException;
import com.common.opthelpersever.entity.FoodList;
import com.common.opthelpersever.mapper.FoodChooseMapper;
import com.common.opthelpersever.service.FoodChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物选择实现类
 **/
@Service
public class FoodChooseServiceImpl implements FoodChooseService {
    @Autowired
    private FoodChooseMapper foodChooseMapper;

    String name = "name";
    @Override
    public List<FoodList> queryFoodList() {
        return foodChooseMapper.queryFoodList();
    }

    @Override
    public int addFoodList(Map<String, String> params) {
        FoodList foodList = new FoodList();
        if (StringUtils.isEmpty(params.get(name))) {
            throw new SeverException(SeverError.PARAMETER_CANNOT_BE_NULL, "name");
        }
        int check = nameCheck(params);
        if (check == 0) {
            foodList.setName(params.get(name));
            foodList.setCreateTime(DateConfig.now());
            foodList.setUpdateTime(DateConfig.now());
            int addResult = foodChooseMapper.addFoodList(foodList);
            return addResult;
        } else {
            return 1;
        }
    }

    /**
     * 新增和修改时名称重复校验
     * liutao
     */
    private int nameCheck(Map<String, String> params) {
        FoodList foodList = new FoodList();
        foodList.setName(params.get(name));
        List<FoodList> queryListByName =foodChooseMapper.queryListByName(foodList);
        if (queryListByName.size() == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
