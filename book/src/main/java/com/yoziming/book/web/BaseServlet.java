package com.yoziming.book.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet", value = "/BaseServlet")
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("UTF-8");
        // 判斷請求的隱藏標籤
        String action = request.getParameter("action");
        try {
            // 用反射找到方法
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,
                    HttpServletResponse.class);
            // 調用方法
            declaredMethod.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 手動拋出異常給過濾器
            throw new RuntimeException(e);
        }
    }
}
