package com.common.opthelperserver.controller;

import com.common.opthelperserver.entity.Drink;
import com.common.opthelperserver.entity.Page;
import com.common.opthelperserver.service.DrinkService;
import com.common.opthelperserver.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author : liutao
 * @Date : 2023/1/6
 * @Description 饮品控制类
 **/
@Slf4j
@RestController
@RequestMapping("/drink")
public class DrinkController {
    private static final Logger logger = LoggerFactory.getLogger(DrinkController.class);

    @Autowired
    private DrinkService drinkService;

    static final int FAILURE = 999;
    static final int REPEAT = 777;

    /**
     * 分页查询所有
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryDrinkList")
    public ResponseResult queryDrinkList(@RequestParam Map<String, String> params) {
        log.info("DrinkController ==>queryDrinkList() params : {}", params);

        String page = params.get("page");
        String pageSize = params.get("pageSize");
        Page<Drink> drink = drinkService.queryDrinkList(params, Integer.parseInt(page), Integer.parseInt(pageSize));
        if (drink.getList() != null) {
            return ResponseResult.success(drink);
        } else {
            return ResponseResult.error("204", "查询无结果!");
        }
    }

    /**
     * 新增
     * @param params
     * @return
     */
    @RequestMapping("/addDrinkList")
    public ResponseResult addDrinkList(@RequestParam Map<String, String> params) {
        logger.info("DrinkController ==>addDrinkList() params : {}", params);

        int addResult = drinkService.addDrinkList(params);
        if (addResult == 0) {
            return ResponseResult.error("新增失败！名称已存在！");
        } else {
            return ResponseResult.success("新增成功！");
        }
    }

    /**
     * 随机查询n个
     * @param n
     * @return
     */
    @RequestMapping("/queryRandomDrinkList")
    public ResponseResult queryRandomDrinkList(@RequestParam Integer n) {
        logger.info("DrinkController ==>queryRandomDrinkList() params : {}", n);

        int page = 1;
        int pageSize = 10;
        Page<Drink> drink = drinkService.queryRandomDrinkList(n, page,pageSize);
        if (drink != null) {
            return ResponseResult.success(drink);
        } else {
            return ResponseResult.error("查询无结果!");
        }
    }

    /**
     * 修改
     * @param params
     * @return
     */
    @RequestMapping("/updateDrinkList")
    public ResponseResult updateDrinkList(@RequestParam Map<String, String> params) {
        logger.info("DrinkController ==>updateDrinkList() params : {}", params);

        int result = drinkService.updateDrinkList(params);
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

    /**
     * 删除
     * @param params
     * @return
     */
    @RequestMapping("/deleteDrinkList")
    public ResponseResult deleteDrinkList(@RequestParam Map<String, String> params) {
        logger.info("DrinkController ==>deleteDrinkList() params : {}", params);

        int result = drinkService.deleteDrinkList(params);
        if (result == FAILURE) {
            return ResponseResult.error("删除失败！");
        } else {
            return ResponseResult.success("删除成功！");
        }
    }
}
