package com.common.opthelperserver.controller;

import com.common.opthelperserver.entity.User;
import com.common.opthelperserver.service.UserService;
import com.common.opthelperserver.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : liutao
 * @Date : 2023/1/4
 * @Description 用户控制类
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        log.info("UserController ==>login() params : {}", user);
        String username = user.getUsername();
        String password = user.getPassword();
        Boolean result = userService.login(username, password);
        if (result) {
            return ResponseResult.success("0", String.valueOf(user));
        }
        return ResponseResult.error("999", "登录失败");
    }
}
