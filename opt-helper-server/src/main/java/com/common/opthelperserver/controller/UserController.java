package com.common.opthelperserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : liutao
 * @Date : 2023/1/4
 * @Description 用户控制类
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login() {
        return "test";
    }
}
