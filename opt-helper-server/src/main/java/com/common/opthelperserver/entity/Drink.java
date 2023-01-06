package com.common.opthelperserver.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : liutao
 * @Date : 2023/1/6
 * @Description 饮品实体类
 **/
@Data
public class Drink implements Serializable {

    private static final long serialVersionUID = 7793384562552178100L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 饮品名称
     */
    private String drinkName;
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
