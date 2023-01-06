package com.common.opthelperserver.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : liutao
 * @Date : 2023/1/5
 * @Description 子菜单实体类
 **/
@Data
public class SubMenus implements Serializable {
    private static final long serialVersionUID = 182530598011681271L;

    /**
     * 主键id
     */
    private int id;
    /**
     * 菜单名
     */
    private String title;
    /**
     * 主菜单id
     */
    private int mid;
    /**
     * 路径
     */
    private String path;
}
