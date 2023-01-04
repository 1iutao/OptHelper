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
    private static final long serialVersionUID = 6432622737519356968L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 食品名称
     */
    private String foodName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 喜欢量
     */
    private String likeNum;
    /**
     * 不喜欢量
     */
    private String unlikeNum;
}
