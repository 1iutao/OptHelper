package com.common.opthelperserver.dao;

import com.common.opthelperserver.entity.MainMenus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : liutao
 * @Date : 2023/1/5
 * @Description 主菜单mapper
 **/
@Mapper
public interface MainMenusMapper {
    /**
     * 查询菜单
     * @return
     */
    List<MainMenus> getMainMenus();
}
