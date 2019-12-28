package com.zfalse.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/23 21:28
 * @Description:
 */
@Data
public class Lost {


    private Integer id;

    private String name;

    private String message;

    private Date time;

    private String finish;

    private String user;

    private String date;

}
