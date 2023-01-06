package com.common.opthelperserver.service.impl;

import com.common.opthelperserver.dao.UserMapper;
import com.common.opthelperserver.entity.User;
import com.common.opthelperserver.exception.ServerError;
import com.common.opthelperserver.exception.ServerException;
import com.common.opthelperserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : liutao
 * @Date : 2023/1/5
 * @Description 用户实现类
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param username
     * @param password
     * @return Result
     */
    @Override
    public Boolean login(String username, String password) {
        Boolean result = false;
        User user = userMapper.getUserByUsername(username);

        if (user == null) {
            throw new ServerException(ServerError.USER_IS_NOT_EXIST, username);
        } else if (!user.getPassword().equals(password)) {
            throw new ServerException(ServerError.PASSWORD_ERROR, password);
        } else if (user != null && user.getPassword().equals(password)) {
            result = true;
        }
        return result;
    }
}
