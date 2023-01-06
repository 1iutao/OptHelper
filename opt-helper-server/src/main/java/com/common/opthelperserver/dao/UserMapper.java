package com.common.opthelperserver.dao;

import com.common.opthelperserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author : liutao
 * @Date : 2023/1/5
 * @Description 用户mapper
 **/
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
