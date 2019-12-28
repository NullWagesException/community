package com.zfalse.service;

import com.zfalse.pojo.Lost;

import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/21 22:21
 * @Description:
 */
public interface LostService {

    /**
     * 查询所有
     * @return
     */
    List<Lost> selectAll();

    /**
     * 查询自己的
     * @return
     */
    List<Lost> selectSelf(String user);

    /**
     * 新增
     * @param lost
     * @return
     */
    int save(Lost lost);

    /**
     * 更新
     * @param lost
     */
    void update(Lost lost);


    List<Lost> selectByPage(Integer page, Integer limit);

    void delete(Integer id);

    List<Lost> selectByPage(Integer page, Integer limit,String user);
}
