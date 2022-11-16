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

    /**
     * 查询
     * @return foodList
     */
    @Override
    public List<FoodList> queryRandomFoodList(int n) {
//        String cacheKey = "foodList:random";
//        String cacheData = (String) redisUtil.get(cacheKey);
        List<FoodList> foodList = foodChooseMapper.queryFoodList();
        List<FoodList> res = new ArrayList<>();
        List<Integer> level = genUniqueRandomVal(0,n-1, n);
        for (int x : level) {
            res.add(foodList.get(x-1));
        }
//        if (cacheData != null) {
//            List foodList = Arrays.asList(cacheData);
//            for (int x : level) {
//                res.add(foodList.get(x-1));
//            }
//
//        } else {
//            List<FoodList> list = foodChooseMapper.queryFoodList();
//            String sList = list.toString();
//            redisUtil.set("foodList", sList);
//            List foodList = Arrays.asList(sList);
//
//        }

        return res;

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



    public List<Integer> genUniqueRandomVal(int minVal, int topVal, int cnt){
        int index;
        int size = topVal-minVal+1;
        if (minVal >= topVal){
            return null;
        }

        if (cnt>size){
            return null;
        }

        if (null == mBaseList){
            mBaseList = new ArrayList<Integer>();
        }

        //初始化基本数据集合
        mBaseList.clear();
        for (int i = minVal; i <= topVal; i++){
            mBaseList.add(i);
        }

        List<Integer> uniqueValList = new ArrayList<Integer>();//无重复的数据集合
        Random random = new Random();
        for (; cnt > 0;){
            index = random.nextInt(size);//范围[0, size)
            uniqueValList.add(mBaseList.get(index));//添加到数据集合
            mBaseList.remove(index);//基本数据集合移除已经加到uniqueValList的数据，这样子就不会重复
            cnt--;
            size--;
        }
        return uniqueValList;
    }
}

