package com.common.opthelperserver.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : liutao
 * @Date : 2023/1/5
 * @Description 主菜单实体类
 **/
@Data
public class MainMenus implements Serializable {
    private static final long serialVersionUID = 7993342117596694672L;

    /**
     * 主键id
     */
    private int id;
    /**
     * 菜单名
     */
    private String title;
    /**
     * 子菜单list
     */
    List<SubMenus> subMenusList;
}
