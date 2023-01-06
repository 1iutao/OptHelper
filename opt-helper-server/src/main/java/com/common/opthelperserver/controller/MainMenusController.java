package com.common.opthelperserver.controller;

import com.alibaba.fastjson.JSON;
import com.common.opthelperserver.dao.MainMenusMapper;
import com.common.opthelperserver.entity.MainMenus;
import com.common.opthelperserver.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : liutao
 * @Date : 2023/1/5
 * @Description 主菜单控制类
 **/
@Slf4j
@RestController
public class MainMenusController {
    @Autowired
    MainMenusMapper mainMenusMapper;

    /**
     * 菜单查询
     * @return
     */
    @RequestMapping("/menus")
    public String getMainMenus() {
        ResponseResult responseResult = new ResponseResult();
        List<MainMenus> menus = mainMenusMapper.getMainMenus();
        log.info("MainMenusController ==>getMainMenus() result: {}", menus);
        if (menus != null) {
            responseResult.put("code", 0);
            responseResult.put("data", menus);
        } else {
            responseResult.put("code", 999);
            responseResult.put("msg", "查询失败");
        }
        String result = JSON.toJSONString(responseResult);
        return result;
    }
}
