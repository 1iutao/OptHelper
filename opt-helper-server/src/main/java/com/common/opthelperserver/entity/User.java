package com.common.opthelperserver.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : liutao
 * @Date : 2023/1/4
 * @Description 用户/管理员实体类
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5795629207297338629L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话
     */
    private String phone;
    /**
     * 权限 0-普通用户 1-管理员
     */
    private String role;
    /**
     * 状态 0-失效 1-有效
     */
    private String status;

}
