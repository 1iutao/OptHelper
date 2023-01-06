package com.common.opthelperserver.dao;

import com.common.opthelperserver.entity.Drink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : liutao
 * @Date : 2023/1/6
 * @Description 饮品mapper
 **/
@Mapper
public interface DrinkMapper {
    /**
     * 分页查询所有
     * @return
     */
    List<Drink> queryDrinkList();

    /**
     * 新增
     * @param drink
     * @return
     */
    int addDrinkList(Drink drink);

    /**
     * 修改
     * @param drink
     * @return
     */
    int updateDrinkList(Drink drink);

    /**
     * 删除
     * @param drink
     * @return
     */
    int deleteDrinkList(Drink drink);

    /**
     * 通过id查询
     * @param drink
     * @return
     */
    List<Drink> queryListById(Drink drink);

    /**
     * 通过name查询
     * @param drink
     * @return
     */
    List<Drink> queryListByName(Drink drink);
}
