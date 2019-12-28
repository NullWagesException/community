package com.zfalse.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zfalse.pojo.*;
import com.zfalse.service.NoticeService;
import com.zfalse.service.PropertyService;
import com.zfalse.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/22 17:59
 * @Description:
 */
public class PropertyAction extends ActionSupport {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String water;

    @Getter
    @Setter
    private String electric;

    @Getter
    @Setter
    private String user;


    @Autowired
    private PropertyService service;

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
     * 查询所有
     * @return
     */
    public String selectSelf(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        result = new ResultMessageDTO(200, ResultDescDTO.OK,service.selectSelf(user.getUsername()));
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
        List<Property> properties = service.selectByPage(page,limit);
        tableDataDTO.setCount(properties.size());
        tableDataDTO.setMsg("查询成功");
        tableDataDTO.setData(properties);
        return "table";
    }

    /**
     * 新增
     * @return
     */
    public String save(){
        Property property = new Property();
        property.setName(name);
        property.setWater(water + "/吨");
        property.setElectric(electric + "/度");
        property.setUser(user);
        int i = service.save(property);
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
        Property property = new Property();
        property.setName(name);
        property.setWater(water + "/吨");
        property.setElectric(electric + "/度");
        property.setUser(user);
        property.setId(id);
        service.update(property);
        result = new ResultMessageDTO(200,ResultDescDTO.OK,"修改成功");
        return Action.SUCCESS;
    }

    /**
     * 修改
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
