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

import java.util.*;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物选择实现类
 **/
@Service
public class FoodChooseServiceImpl implements FoodChooseService {

    static List<Integer> mBaseList = null;

    @Autowired
    private FoodChooseMapper foodChooseMapper;
    @Autowired
    private RedisUtil redisUtil;
    String foodName = "foodName";

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

    /**
     * 查询
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
        FoodList foodList = new FoodList();
        if (StringUtils.isEmpty(params.get(foodName))) {
            throw new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, "name");
        }
        int check = nameCheck(params);
        if (check == 0) {
            foodList.setFoodName(params.get(foodName));
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

