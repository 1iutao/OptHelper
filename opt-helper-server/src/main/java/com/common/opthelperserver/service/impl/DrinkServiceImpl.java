package com.common.opthelperserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.opthelperserver.dao.DrinkMapper;
import com.common.opthelperserver.entity.Drink;
import com.common.opthelperserver.entity.Page;
import com.common.opthelperserver.exception.ServerError;
import com.common.opthelperserver.exception.ServerException;
import com.common.opthelperserver.service.DrinkService;
import com.common.opthelperserver.utils.DateUtil;
import com.common.opthelperserver.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author : liutao
 * @Date : 2023/1/6
 * @Description 饮品实现类
 **/
@Service
public class DrinkServiceImpl implements DrinkService {

    private static final Logger logger = LoggerFactory.getLogger(DrinkServiceImpl.class);

    @Autowired
    private DrinkMapper drinkMapper;
    @Autowired
    private RedisUtil redisUtil;

    static final int FAILURE = 999;
    static final int REPEAT = 777;

    String drinkName = "drinkName";
    String id = "id";

    @Override
    public Page<Drink> queryDrinkList(Map<String, String> params, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        String cacheKey = "drinkList";
        if (redisUtil.hasKey(cacheKey)) {
            Object cacheData = redisUtil.get(cacheKey);
            List<Drink> list = JSONObject.parseArray((String) cacheData, Drink.class);
            Page<Drink> drinkPage = new Page<>(list);
            return drinkPage;
        } else {
            List<Drink> list = drinkMapper.queryDrinkList();
            String sList = JSONObject.toJSON(list).toString();
            redisUtil.set("drinkList", sList, 200);
            Page<Drink> drinkPage = new Page<>(list);
            return drinkPage;
        }
    }

    @Override
    public int addDrinkList(Map<String, String> params) {
        redisUtil.del("drinkList");
        Drink drink = new Drink();
        if (StringUtils.isEmpty(params.get(drinkName))) {
            logger.error("饮品名称不能为空");
            throw new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, "drinkName");
        }
        int check = nameCheck(params);
        if (check == 0) {
            drink.setDrinkName(params.get(drinkName));
            drink.setCreateTime(DateUtil.now());
            drink.setUpdateTime(DateUtil.now());
            int addResult = drinkMapper.addDrinkList(drink);
            return addResult;
        } else {
            return 0;
        }
    }

    @Override
    public Page<Drink> queryRandomDrinkList(Integer n, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Drink> drink = (List<Drink>) drinkMapper.queryDrinkList();
        List<Drink> res = new ArrayList<>();
        List<Integer> level = getNumber(0, drink.size()-1, n);
        for (int x : level) {
            res.add(drink.get(x));
        }
        Page<Drink> drinkPage = new Page<>(res);
        return drinkPage;
    }

    @Override
    public int updateDrinkList(Map<String, String> params) {
        redisUtil.del("drinkList");
        if (StringUtils.isEmpty(params.get(id))) {
            logger.error("饮品id不能为空");
            throw  new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, id);
        }
        if (StringUtils.isEmpty(params.get(drinkName))) {
            logger.error("饮品名称不能为空");
            throw  new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, drinkName);
        }
        Drink drink = new Drink();
        drink.setDrinkName(params.get(drinkName));
        drink.setId(Integer.valueOf(params.get(id)));
        drink.setUpdateTime(DateUtil.now());

        List<Drink> queryListById = drinkMapper.queryListById(drink);
        if (queryListById.size() == 0) {
            return FAILURE;
        } else if (queryListById.size() == 1) {
            Drink drink1 = queryListById.get(0);
            if (drink1.getDrinkName().equals(params.get(drinkName))) {
                return REPEAT;
            } else {
                int result = drinkMapper.updateDrinkList(drink);
                return result;
            }
        }
        return 0;
    }

    @Override
    public int deleteDrinkList(Map<String, String> params) {
        redisUtil.del("drinkList");
        if (StringUtils.isEmpty(params.get(id))) {
            logger.error("饮品id不能为空");
            throw  new ServerException(ServerError.PARAMETER_CANNOT_BE_NULL, id);
        }
        Drink drink = new Drink();
        drink.setId(Integer.valueOf(params.get(id)));
        List<Drink> queryListById = drinkMapper.queryListById(drink);
        if (queryListById.size() != 0) {
            drink.setId(Integer.valueOf(params.get(id)));
            int result = drinkMapper.deleteDrinkList(drink);
            return result;
        }
        return FAILURE;
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

    private int nameCheck(Map<String, String> params) {
        Drink drink = new Drink();
        drink.setDrinkName(params.get(drinkName));
        List<Drink> queryListByName = drinkMapper.queryListByName(drink);
        if (queryListByName.size() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

}
