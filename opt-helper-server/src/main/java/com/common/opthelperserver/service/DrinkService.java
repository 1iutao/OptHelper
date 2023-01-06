package com.common.opthelperserver.service;

import com.common.opthelperserver.entity.Drink;
import com.common.opthelperserver.entity.Page;

import java.util.Map;

/**
 * @Author : liutao
 * @Date : 2023/1/6
 * @Description 饮品service
 **/
public interface DrinkService {
    /**
     * 查询所有
     * @param params
     * @param page
     * @param pageSize
     * @return
     */
    Page<Drink> queryDrinkList(Map<String, String> params, int page, int pageSize);

    /**
     * 新增
     * @param params
     * @return int
     */
    int addDrinkList(Map<String, String> params);

    /**
     * 随机查询n个
     * @param n
     * @param page
     * @param pageSize
     * @return
     */
    Page<Drink> queryRandomDrinkList(Integer n, int page, int pageSize);

    /**
     * 修改
     * @param params
     * @return int
     */
    int updateDrinkList(Map<String, String> params);

    /**
     * 删除
     * @param params
     * @return int
     */
    int deleteDrinkList(Map<String, String> params);
}
