package com.common.opthelperserver.service;

/**
 * @Author : liutao
 * @Date : 2023/1/5
 * @Description ...
 **/
public interface UserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Boolean login(String username, String password);
}
