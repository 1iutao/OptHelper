package com.common.opthelperserver.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物列表实体类
 **/
@Data
public class FoodList implements Serializable {
    private Integer id;
    private String foodName;
    private String createTime;
    private String updateTime;
}
