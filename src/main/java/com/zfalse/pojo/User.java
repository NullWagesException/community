package com.zfalse.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/21 20:17
 * @Description:
 */
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String realname;

    private String isadmin;

}
