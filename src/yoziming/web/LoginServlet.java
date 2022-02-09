package yoziming.web;

import yoziming.pojo.User;
import yoziming.service.UserService;
import yoziming.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、獲取請求的參數
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 調用 userService.login()登入處理業務
        User loginUser = userService.login(new User(null, username, password, null));
        // 如果等於null,說明登入 失敗!
        if (loginUser == null) {
            // 把錯誤信息，和回顯的表單項信息，保存到Request域中
            req.setAttribute("msg","用戶或密碼錯誤！");
            req.setAttribute("username", username);
            //   跳回登入頁面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登入 成功
            //跳到成功頁面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
}
