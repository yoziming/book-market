package com.yoziming.book.web;

import com.yoziming.book.pojo.Cart;
import com.yoziming.book.pojo.Order;
import com.yoziming.book.pojo.OrderItem;
import com.yoziming.book.pojo.User;
import com.yoziming.book.service.OrderService;
import com.yoziming.book.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        // 先獲取用戶
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 未登入
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        int userId = user.getId();
        Cart cart = (Cart) session.getAttribute("cart");
        String orderId = orderService.createOrder(cart, userId);
        // 清空後跳轉到成功
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp?orderId=" + orderId);
    }

    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        // 先獲取用戶
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 未登入
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        if (user.getId() <= 3) {
            // 管理員id
            List<Order> orders = orderService.showAllOrders();
            session.setAttribute("orders", orders);
            // 跳轉
            request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        // 先獲取用戶
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 未登入
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        if (user.getId() <= 3) {
            String orderId = request.getParameter("orderId");
            orderService.sendOrder(orderId);
            // 跳轉
            response.sendRedirect(request.getContextPath() + "/OrderServlet?action=showAllOrders");
        } else {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        // 先獲取用戶
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 未登入
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        session.setAttribute("orderItems", orderItems);
        // 跳轉
        response.sendRedirect(request.getContextPath() + "/pages/order/order_detail.jsp?orderId=" + orderId);
    }

    protected void showMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        // 先獲取用戶
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 未登入
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        int userId = user.getId();
        List<Order> orders = orderService.showMyOrders(userId);
        session.setAttribute("orders", orders);
        // 跳轉
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    }

    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        // 先獲取用戶
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 未登入
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        String orderId = request.getParameter("orderId");
        orderService.receiveOrder(orderId);
        // 跳轉
        response.sendRedirect(request.getContextPath() + "/OrderServlet?action=showMyOrders");

    }
}
