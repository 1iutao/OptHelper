package com.common.opthelperserver.service.impl;

import com.common.opthelperserver.utils.DateUtil;
import com.common.opthelperserver.exception.ServerError;
import com.common.opthelperserver.exception.ServerException;
import com.common.opthelperserver.entity.FoodList;
import com.common.opthelperserver.mapper.FoodChooseMapper;
import com.common.opthelperserver.service.FoodChooseService;
import com.common.opthelperserver.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
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
    @Autowired
    private RedisUtil redisUtil;
    String name = "name";

    /**
     * 查询
     * @return foodList
     */
    @Override
    public List<FoodList> queryFoodList() {

        String cacheKey = "foodList";
        String cacheData = (String) redisUtil.get(cacheKey);
        if (cacheData != null) {
            List foodList = Arrays.asList(cacheData);
            return foodList;
        } else {
            List<FoodList> list = foodChooseMapper.queryFoodList();
            String sList = list.toString();
            redisUtil.set("foodList", sList);
            List foodList = Arrays.asList(sList);

            return foodList;
        }
    }

    @Override
    public int addFoodList(Map<String, String> params) {
        FoodList foodList = new FoodList();
        if (StringUtils.isEmpty(params.get(name))) {
            throw new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, "name");
        }
        int check = nameCheck(params);
        if (check == 0) {
            foodList.setName(params.get(name));
            foodList.setCreateTime(DateUtil.now());
            foodList.setUpdateTime(DateUtil.now());
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
