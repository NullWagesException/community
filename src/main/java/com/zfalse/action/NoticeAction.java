package com.zfalse.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zfalse.pojo.Notice;
import com.zfalse.pojo.ResultDescDTO;
import com.zfalse.pojo.ResultMessageDTO;
import com.zfalse.pojo.TableDataDTO;
import com.zfalse.service.NoticeService;
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
public class NoticeAction extends ActionSupport {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Date time;

    @Getter
    @Setter
    private String text;



    @Autowired
    private NoticeService service;

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
    public String selectTable(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        tableDataDTO = new TableDataDTO();
        tableDataDTO.setCode(0);
        List<Notice> notices = service.selectByPage(page,limit);
        tableDataDTO.setCount(notices.size());
        tableDataDTO.setMsg("查询成功");
        for (Notice notice : notices) {
            notice.setDate(DateUtil.dateToString(notice.getTime(), DateUtil.FORMAT_TWO));
        }
        tableDataDTO.setData(notices);
        return "table";
    }

    /**
     * 新增
     * @return
     */
    public String save(){
        Notice notice = new Notice();
        notice.setText(text);
        notice.setTime(new Date());
        int i = service.save(notice);
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
        Notice notice = new Notice();
        notice.setText(text);
        notice.setId(id);
        notice.setTime(time);
        service.update(notice);
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
