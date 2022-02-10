package yoziming.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解決post請求中文亂碼問題
        // 一定要在獲取請求參數之前調用才有效
        req.setCharacterEncoding("UTF-8");
        // 解決響應的中文亂碼
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");
        try {
            // 獲取action業務鑒別字符串，獲取相應的業務 方法反射物件
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 測試
            System.out.println(method);
            // 調用目標業務 方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            // 把異常拋給Filter過濾器
            throw new RuntimeException(e);
        }
    }

}
