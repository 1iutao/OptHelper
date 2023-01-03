package com.common.opthelperserver.controller;

import com.common.opthelperserver.utils.ResponseResult;
import com.common.opthelperserver.entity.FoodList;
import com.common.opthelperserver.service.FoodChooseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食品选择控制类
 **/
@Slf4j
@RestController
@RequestMapping("/foodChoose")
public class FoodChooseController {

    @Autowired
    private FoodChooseService foodChooseService;
    private Integer FAILURE = 999;
    private Integer REPEAT = 777;
    @RequestMapping("/queryFoodList")
    public ResponseResult queryFoodList() {

        List<FoodList> foodList = foodChooseService.queryFoodList();
        log.info("FoodChooseController ==>queryFoodList() result : {}", foodList);
        if (foodList != null) {
            return ResponseResult.success(foodList);
        } else {
            return ResponseResult.error("查询无结果!");
        }
    }

    @RequestMapping("/addFoodList")
    public ResponseResult addFoodList(@RequestParam Map<String, String> params) {
        log.info("FoodChooseController ==>addFoodList() params : {}", params);

        int addResult = foodChooseService.addFoodList(params);
        if (addResult == 0) {
            return ResponseResult.error("新增失败！名称已存在！");
        } else {
            return ResponseResult.success("新增成功！");
        }
    }

    @RequestMapping("/queryRandomFoodList")
    public ResponseResult queryRandomFoodList(@RequestParam Integer n) {

        List<FoodList> foodList = foodChooseService.queryRandomFoodList(n);
        log.info("FoodChooseController ==>queryRandomFoodList() result : {}", foodList);
        if (foodList != null) {
            return ResponseResult.success(foodList);
        } else {
            return ResponseResult.error("查询无结果!");
        }
    }

    @RequestMapping("/updateFoodList")
    public ResponseResult updateFoodList(@RequestParam Map<String, String> params) {
        log.info("FoodChooseController ==>updateFoodList() params : {}", params);

        int result = foodChooseService.updateFoodList(params);
        if (result == FAILURE) {
            return ResponseResult.error("修改失败！不存在此项！");
        } else if (result == 0) {
            return ResponseResult.error("修改失败！");
        } else if (result == REPEAT) {
            return ResponseResult.error("修改失败，名称重复！");
        } else {
            return ResponseResult.success("修改成功！");
        }
    }

    @RequestMapping("/deleteFoodList")
    public ResponseResult deleteFoodList(@RequestParam Map<String, String> params) {
        log.info("FoodChooseController ==>deleteFoodList() params : {}", params);

        int result = foodChooseService.deleteFoodList(params);
        if (result == FAILURE) {
            return ResponseResult.error("删除失败！");
        } else {
            return ResponseResult.success("删除成功！");
        }
    }

}
