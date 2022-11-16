package com.common.opthelperserver.controller;

import com.common.opthelperserver.ResponseResult;
import com.common.opthelperserver.entity.FoodList;
import com.common.opthelperserver.service.FoodChooseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/queryFoodList")
    public ResponseResult queryFoodList() {

        List<FoodList> foodList = foodChooseService.queryFoodList();
        if (foodList != null) {
            return ResponseResult.list(foodList);
        } else {
            return ResponseResult.error("查询无结果!");
        }
    }

    @RequestMapping("/addFoodList")
    public ResponseResult addFoodList(@RequestParam Map<String, String> params) {
        log.debug("FoodChooseController ==>addFoodList() params : {}", params);

        int addResult = foodChooseService.addFoodList(params);
        if (addResult == 0) {
            return ResponseResult.error("新增失败！");
        } else {
            return ResponseResult.success("新增成功！");
        }
    }

}
