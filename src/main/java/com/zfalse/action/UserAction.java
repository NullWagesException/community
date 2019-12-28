package com.zfalse.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zfalse.pojo.ResultDescDTO;
import com.zfalse.pojo.ResultMessageDTO;
import com.zfalse.pojo.TableDataDTO;
import com.zfalse.utils.JSONResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.zfalse.pojo.User;
import com.zfalse.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/21 20:19
 * @Description:
 */

public class UserAction extends ActionSupport {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String realname;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String passwordNew;

    @Getter
    @Setter
    private Integer id;

    @Setter
    @Getter
    private TableDataDTO tableDataDTO;

    @Getter
    @Setter
    private ResultMessageDTO result;

    @Autowired
    private UserService userService;



    public String login(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userService.login(user);
        if (user != null){
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("user", user);
            result = new ResultMessageDTO(200,ResultDescDTO.OK,"登录成功");
        }else{
            result = new ResultMessageDTO(200,ResultDescDTO.FAIL,"登录失败");
        }
        return Action.SUCCESS;
    }

    public String register(){
        User user = new User();
        user.setRealname(realname);
        user.setUsername(username);
        user.setPassword(password);
        user.setIsadmin("0");
        int i = userService.register(user);
        if (i > 0){
            result = new ResultMessageDTO(200,ResultDescDTO.OK,"注册成功");
        }else if(i == -1){
            result = new ResultMessageDTO(200,ResultDescDTO.FAIL,"注册失败，用户名已存在");
        }else{
            result = new ResultMessageDTO(200,ResultDescDTO.FAIL,"注册失败");
        }
        return Action.SUCCESS;
    }

    public String getUser(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            result = new ResultMessageDTO(201, ResultDescDTO.OK, user);
        }else {
            result = new ResultMessageDTO(200,ResultDescDTO.FAIL,"请登录");
        }
        return Action.SUCCESS;
    }

    public String loginout(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("user", null);
        result = new ResultMessageDTO(201, ResultDescDTO.OK, "退出成功");
        return Action.SUCCESS;
    }

    public String update(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            if (!user.getPassword().equals(password)){
                result = new ResultMessageDTO(200,ResultDescDTO.FAIL,"请输入正确的密码");
            }else{
                if (StringUtils.isEmpty(passwordNew)) {
                    user.setPassword(passwordNew);
                }
                user.setRealname(realname);
                userService.update(user);
                result = new ResultMessageDTO(200, ResultDescDTO.OK, "更新成功");
            }
        }else {
            result = new ResultMessageDTO(200,ResultDescDTO.FAIL,"请登录");
        }
        return Action.SUCCESS;
    }

    public String updateAdmin(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = new User();
        user.setId(id);
        user.setRealname(realname);
        user.setPassword(password);
        user.setUsername(username);
        userService.update(user);
        result = new ResultMessageDTO(200, ResultDescDTO.OK, "更新成功");
        return Action.SUCCESS;
    }


    /**
     * 查询所有
     * @return
     */
    public String selectTable(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        tableDataDTO = new TableDataDTO();
        tableDataDTO.setCode(0);
        List<User> properties = userService.selectByPage(page,limit);
        tableDataDTO.setCount(properties.size());
        tableDataDTO.setMsg("查询成功");
        tableDataDTO.setData(properties);
        return "table";
    }

    /**
     * 查询所有
     * @return
     */
    public String delete(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user = new User();
        user.setId(id);
        userService.delete(user);
        result = new ResultMessageDTO(200,ResultDescDTO.OK,"删除成功");
        return Action.SUCCESS;
    }
}
