package com.zfalse.service;

import com.zfalse.pojo.FeedBack;

import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/21 22:21
 * @Description:
 */
public interface FeedBackService {

    /**
     * 查询所有
     * @return
     */
    List<FeedBack> selectAll();

    /**
     * 查询自己的
     * @return
     */
    List<FeedBack> selectSelf(String user);

    /**
     * 新增
     * @param FeedBack
     * @return
     */
    int save(FeedBack feedBack);

    /**
     * 更新
     * @param feedBack
     */
    void update(FeedBack feedBack);


    List<FeedBack> selectByPage(Integer page, Integer limit);

    void delete(Integer id);

    List<FeedBack> selectByPage(Integer page, Integer limit, String user);
}
