package com.common.opthelperserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.opthelperserver.utils.DateUtil;
import com.common.opthelperserver.exception.ServerError;
import com.common.opthelperserver.exception.ServerException;
import com.common.opthelperserver.entity.Food;
import com.common.opthelperserver.dao.FoodMapper;
import com.common.opthelperserver.service.FoodService;
import com.common.opthelperserver.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物选择实现类
 **/
@Service
public class FoodServiceImpl implements FoodService {

    private static final Logger logger = LoggerFactory.getLogger(FoodService.class);

    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private RedisUtil redisUtil;

    static final int FAILURE = 999;
    static final int REPEAT = 777;

    String foodName = "foodName";
    String id = "id";

    /**
     * 查询
     * @return foodList
     */
    @Override
    public List<Food> queryFoodList(Map<String, String> params) {

        String cacheKey = "foodList";
        if (redisUtil.hasKey(cacheKey)) {
            Object cacheData = redisUtil.get(cacheKey);
            return  JSONObject.parseArray((String) cacheData, Food.class);
        } else {
            List<Food> list = foodMapper.queryFoodList();
            String sList = JSONObject.toJSON(list).toString();
            redisUtil.set("foodList", sList, 200);

            return list;
        }
    }

    /**
     * 随机查询
     * @return foodList
     */
    @Override
    public List<Food> queryRandomFoodList(Integer n) {

        List<Food> food = foodMapper.queryFoodList();
        List<Food> res = new ArrayList<>();
        List<Integer> level = getNumber(0, food.size()-1, n);
        for (int x : level) {
            res.add(food.get(x));

        }
        return res;

    }

    @Override
    public int addFoodList(Map<String, String> params) {
        redisUtil.del("foodList");
        Food food = new Food();
        if (StringUtils.isEmpty(params.get(foodName))) {
            logger.error("食品名称不能为空");
            throw new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, "foodName");
        }
        int check = nameCheck(params);
        if (check == 0) {
            food.setFoodName(params.get(foodName));
            food.setCreateTime(DateUtil.now());
            food.setUpdateTime(DateUtil.now());
            int addResult = foodMapper.addFoodList(food);
            return addResult;
        } else {
            return 0;
        }
    }

    @Override
    public int updateFoodList(Map<String, String> params) {
        redisUtil.del("foodList");
        if (StringUtils.isEmpty(params.get(id))) {
            logger.error("食品id不能为空");
            throw  new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, id);
        }
        if (StringUtils.isEmpty(params.get(foodName))) {
            logger.error("食品名称不能为空");
            throw  new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, "foodName");
        }
        Food food = new Food();
        food.setFoodName(params.get(foodName));
        food.setId(Integer.valueOf(params.get(id)));
        food.setUpdateTime(DateUtil.now());

        List<Food> queryListById = foodMapper.queryListById(food);
        if (queryListById.size() == 0) {
            return FAILURE;
        } else if (queryListById.size() == 1) {
            Food food1 = queryListById.get(0);
            if (food1.getFoodName().equals(params.get(foodName))) {
                return REPEAT;
            } else {
                int result = foodMapper.updateFoodList(food);
                return result;
            }
        }
        return 0;
    }

    @Override
    public int deleteFoodList(Map<String, String> params) {
        redisUtil.del("foodList");
        if (StringUtils.isEmpty(params.get(id))) {
            logger.error("食品id不能为空");
            throw  new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, id);
        }
        Food food = new Food();
        food.setId(Integer.valueOf(params.get(id)));
        List<Food> queryListById = foodMapper.queryListById(food);
        if (queryListById.size() != 0) {
            food.setId(Integer.valueOf(params.get(id)));
            int result = foodMapper.deleteFoodList(food);
            return result;
        }
        return FAILURE;
    }

    /**
     * 新增和修改时名称重复校验
     * liutao
     */
    private int nameCheck(Map<String, String> params) {
        Food food = new Food();
        food.setFoodName(params.get(foodName));
        List<Food> queryListByName = foodMapper.queryListByName(food);
        if (queryListByName.size() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public List<Integer> getNumber(Integer start,Integer end,Integer n){
        if(start > end){
            throw new RuntimeException("开始数字不得大于结束数字");
        }
        if( (end-start) < n){
            throw new RuntimeException("范围内的数字个数不得小于取出的数量");
        }
        List<Integer> finalNumber = new ArrayList<>();
        while (finalNumber.size() < n){
            Random random = new Random();
            Integer randomNumber = random.nextInt(end-start+1)+start;
            if (!finalNumber.contains(randomNumber)) {
                finalNumber.add(randomNumber);
            }
        }
        return  finalNumber;
    }

}

