package com.zfalse.service;

import com.zfalse.pojo.User;

import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/21 22:21
 * @Description:
 */
public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 注册
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 根据名称查询用户
     * @param username
     * @return
     */
    List<User> selectUserByName(String username);

    /**
     * 根据名称和密码查询
     * @param username
     * @param password
     * @return
     */
    List<User> selectUserByNamePWD(String username, String password);

    void update(User user);

    List<User> selectByPage(Integer page, Integer limit);

    void delete(User user);
}
