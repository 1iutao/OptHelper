package com.common.opthelpersever.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/14
 * @Description 食物列表实体类
 **/
@Data
public class FoodList implements Serializable {
    private Integer id;
    private String name;
    private Date createTime;
    private Date updateTime;
}
