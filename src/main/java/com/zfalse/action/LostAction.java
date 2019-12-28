package com.zfalse.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zfalse.pojo.*;
import com.zfalse.service.LostService;
import com.zfalse.service.PropertyService;
import com.zfalse.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/22 17:59
 * @Description:
 */
public class LostAction extends ActionSupport {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String finish;

    @Getter
    @Setter
    private String user;


    @Autowired
    private LostService service;

    @Getter
    @Setter
    private ResultMessageDTO result;

    @Setter
    @Getter
    private TableDataDTO tableDataDTO;

    /**
     * 查询所有
     * @return
     */
    public String selectAll(){
        result = new ResultMessageDTO(200, ResultDescDTO.OK,service.selectAll());
        return Action.SUCCESS;
    }

    /**
     * 查询自己所有
     * @return
     */
    public String selectSelf(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        tableDataDTO = new TableDataDTO();
        tableDataDTO.setCode(0);
        List<Lost> properties = new ArrayList<>();
        if ("1".equals(user.getIsadmin())){
            properties = service.selectAll();
        }else{
            properties = service.selectByPage(page,limit,user.getUsername());
        }
        tableDataDTO.setCount(properties.size());
        for (Lost lost : properties) {
            lost.setDate(DateUtil.dateToString(lost.getTime(), DateUtil.FORMAT_TWO));
        }
        tableDataDTO.setMsg("查询成功");
        tableDataDTO.setData(properties);
        return "table";
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
        List<Lost> properties = service.selectByPage(page,limit);
        tableDataDTO.setCount(properties.size());
        tableDataDTO.setMsg("查询成功");
        for (Lost lost : properties) {
            lost.setDate(DateUtil.dateToString(lost.getTime(), DateUtil.FORMAT_TWO));
        }
        tableDataDTO.setData(properties);

        return "table";
    }

    /**
     * 新增
     * @return
     */
    public String save(){
        Lost lost = new Lost();
        lost.setName(name);
        lost.setMessage(message);
        lost.setFinish(finish);
        lost.setTime(new Date());
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        lost.setUser(user.getUsername());
        int i = service.save(lost);
        if (i > 0){
            result = new ResultMessageDTO(200,ResultDescDTO.OK,"添加成功");
        }else{
            result = new ResultMessageDTO(200,ResultDescDTO.FAIL,"添加失败");
        }
        return Action.SUCCESS;
    }

    /**
     * 修改
     * @return
     */
    public String update(){
        Lost lost = new Lost();
        lost.setName(name);
        lost.setMessage(message);
        lost.setFinish(finish);
        lost.setTime(new Date());
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        lost.setUser(user.getUsername());
        lost.setId(id);
        service.update(lost);
        result = new ResultMessageDTO(200,ResultDescDTO.OK,"修改成功");
        return Action.SUCCESS;
    }

    /**
     * 删除
     * @return
     */
    public String delete(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer id = Integer.valueOf(request.getParameter("id"));
        service.delete(id);
        result = new ResultMessageDTO(200,ResultDescDTO.OK,"删除成功");
        return Action.SUCCESS;
    }

}
