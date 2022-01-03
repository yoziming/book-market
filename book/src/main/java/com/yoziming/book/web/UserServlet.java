package com.yoziming.book.web;

import com.google.gson.Gson;
import com.yoziming.book.pojo.User;
import com.yoziming.book.service.UserService;
import com.yoziming.book.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 接收參數
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 跟資料庫驗證
        User loginUser = userService.login(new User(username, password, null));
        if (loginUser != null) {
            // 成功，將用戶資訊存到session域
            request.getSession().setAttribute("user", loginUser);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        } else {
            // 登入失敗
            request.setAttribute("msg", "密碼錯誤!");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    protected void existsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 接收參數
        String username = request.getParameter("username");
        // 跟資料庫驗證
        boolean existsUsername = userService.existsUsername(username);
        // 封裝結果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        resultMap.put("msg", "用戶名已存在!");
        Gson gson = new Gson();
        String resultString = gson.toJson(resultMap);
        // 丟回給前端
        response.getWriter().write(resultString);
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 接收參數值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 檢查驗證碼
        // 獲取這次請求的驗證密鑰號碼
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 馬上把session中的值刪掉，阻止重複提交多次註冊
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if (token.equalsIgnoreCase(code)) {

            // 檢查名稱是否已存在
            if (userService.existsUsername(username)) {
                // 返回提示並回寫
                request.setAttribute("msg", "用戶名已存在！");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);

            } else {
                // 註冊
                // User user = new User(username, password, email);
                userService.registUser(user);
                // 跳轉到註冊成功頁面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

            }

            // 檢查驗證碼失敗
        } else {
            // 註冊失敗，重新註冊
            // 把回顯資訊，保存到Request域中
            request.setAttribute("msg", "驗證碼錯誤！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }

}


