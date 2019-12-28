package com.zfalse.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/22 0:48
 * @Description:
 */
public class JSONResult {

    public static void outPut(HttpServletRequest request, HttpServletResponse response, String json) throws IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }

}
