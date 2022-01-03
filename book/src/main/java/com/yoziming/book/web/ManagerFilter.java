package com.yoziming.book.web;

import com.yoziming.book.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "ManagerFilter", urlPatterns = {"/pages/manager/*", "/manager/*", "/BookServlet"})
public class ManagerFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException
            , IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else if (user.getId() <= 3) {
            // 判斷為管理員ID
            System.out.println("有管理員來操作");
            chain.doFilter(request, response);
        } else {
            System.out.println("雖然登入但不是管理員");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }
}
