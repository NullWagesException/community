package com.zfalse.service;

import com.zfalse.pojo.Notice;

import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/21 22:21
 * @Description:
 */
public interface NoticeService {

    /**
     * 查询所有
     * @return
     */
    List<Notice> selectAll();

    /**
     * 新增
     * @param notice
     * @return
     */
    int save(Notice notice);

    /**
     * 更新
     * @param notice
     */
    void update(Notice notice);

    List<Notice> selectByPage(Integer page, Integer limit);

    void delete(Integer id);
}
