package com.common.opthelperserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.opthelperserver.utils.DateUtil;
import com.common.opthelperserver.exception.ServerError;
import com.common.opthelperserver.exception.ServerException;
import com.common.opthelperserver.entity.FoodList;
import com.common.opthelperserver.dao.FoodChooseMapper;
import com.common.opthelperserver.service.FoodChooseService;
import com.common.opthelperserver.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物选择实现类
 **/
@Service
public class FoodChooseServiceImpl implements FoodChooseService {

    private static final Logger logger = LoggerFactory.getLogger(FoodChooseService.class);

    @Autowired
    private FoodChooseMapper foodChooseMapper;
    @Autowired
    private RedisUtil redisUtil;

    private Integer FAILURE = 999;
    private Integer REPEAT = 777;

    String foodName = "foodName";
    String id = "id";

    /**
     * 查询
     * @return foodList
     */
    @Override
    public List<FoodList> queryFoodList() {

        String cacheKey = "foodList";
        if (redisUtil.hasKey(cacheKey)) {
            Object cacheData = redisUtil.get(cacheKey);
            return  JSONObject.parseArray((String) cacheData, FoodList.class);
        } else {
            List<FoodList> list = foodChooseMapper.queryFoodList();
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
    public List<FoodList> queryRandomFoodList(Integer n) {
//        String cacheKey = "foodList:random";
//        String cacheData = (String) redisUtil.get(cacheKey);
        List<FoodList> foodList = foodChooseMapper.queryFoodList();
        List<FoodList> res = new ArrayList<>();
        List<Integer> level = getNumber(0,foodList.size()-1, n);
        for (int x : level) {
            res.add(foodList.get(x));

        }
        return res;

    }

    @Override
    public int addFoodList(Map<String, String> params) {
        redisUtil.del("foodList");
        FoodList foodList = new FoodList();
        if (StringUtils.isEmpty(params.get(foodName))) {
            logger.error("食品名称不能为空");
            throw new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, "foodName");
        }
        int check = nameCheck(params);
        if (check == 0) {
            foodList.setFoodName(params.get(foodName));
            foodList.setCreateTime(DateUtil.now());
            foodList.setUpdateTime(DateUtil.now());
            int addResult = foodChooseMapper.addFoodList(foodList);
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
        FoodList foodList = new FoodList();
        foodList.setFoodName(params.get(foodName));
        foodList.setId(Integer.valueOf(params.get(id)));
        foodList.setUpdateTime(DateUtil.now());

        List<FoodList> queryListById =foodChooseMapper.queryListById(foodList);
        if (queryListById.size() == 0) {
            return FAILURE;
        } else if (queryListById.size() == 1) {
            FoodList foodList1 = queryListById.get(0);
            if (foodList1.getFoodName().equals(params.get(foodName))) {
                return REPEAT;
            } else {
                int result = foodChooseMapper.updateFoodList(foodList);
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
        FoodList foodList = new FoodList();
        foodList.setId(Integer.valueOf(params.get(id)));
        List<FoodList> queryListById = foodChooseMapper.queryListById(foodList);
        if (queryListById.size() != 0) {
            foodList.setId(Integer.valueOf(params.get(id)));
            int result = foodChooseMapper.deleteFoodList(foodList);
            return result;
        }
        return FAILURE;
    }

    /**
     * 新增和修改时名称重复校验
     * liutao
     */
    private int nameCheck(Map<String, String> params) {
        FoodList foodList = new FoodList();
        foodList.setFoodName(params.get(foodName));
        List<FoodList> queryListByName =foodChooseMapper.queryListByName(foodList);
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

