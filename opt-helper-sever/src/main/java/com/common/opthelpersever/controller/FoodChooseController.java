package com.common.opthelpersever.controller;

import com.common.opthelpersever.config.ResponseResult;
import com.common.opthelpersever.entity.FoodList;
import com.common.opthelpersever.service.FoodChooseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食品选择控制类
 **/
@Slf4j
@Controller
@RequestMapping("/foodChoose")
public class FoodChooseController {
    @Autowired
    private FoodChooseService foodChooseService;

    @RequestMapping("/queryFoodList")
    public ResponseResult queryFoodList() {
        log.debug("FoodChooseController ==>queryFoodList() params : {}");

        List<FoodList> foodList = foodChooseService.queryFoodList();
        if (foodList != null) {
            return ResponseResult.success("查询成功！");
        } else {
            return ResponseResult.error("查询无结果");
        }
    }

}
