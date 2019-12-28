package com.zfalse.service;

import com.zfalse.pojo.Property;

import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/21 22:21
 * @Description:
 */
public interface PropertyService {

    /**
     * 查询所有
     * @return
     */
    List<Property> selectAll();

    /**
     * 查询自己的
     * @return
     */
    Property selectSelf(String user);

    /**
     * 新增
     * @param property
     * @return
     */
    int save(Property property);

    /**
     * 更新
     * @param property
     */
    void update(Property property);


    List<Property> selectByPage(Integer page, Integer limit);

    void delete(Integer id);
}
