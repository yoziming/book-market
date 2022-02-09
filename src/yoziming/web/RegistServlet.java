package yoziming.web;

import yoziming.pojo.User;
import yoziming.service.UserService;
import yoziming.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、獲取請求的參數
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


        // 2、檢查 驗證碼是否正確  === 寫死,要求驗證碼為:abcde
        if ("abcde".equalsIgnoreCase(code)) {
            // 3、檢查 用戶名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用戶名[" + username + "]已存在!");

                // 把回顯信息，保存到Request域中
                req.setAttribute("msg", "用戶名已存在！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                // 跳回註冊頁面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // 調用Sservice保存到資料庫
                userService.registUser(new User(null, username, password, email));
                // 跳到註冊成功頁面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // 把回顯信息，保存到Request域中
            req.setAttribute("msg", "驗證碼錯誤！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            System.out.println("驗證碼[" + code + "]錯誤");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}
