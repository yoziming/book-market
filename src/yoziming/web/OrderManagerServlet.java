package yoziming.web;

import yoziming.pojo.*;
import yoziming.service.OrderService;
import yoziming.service.impl.OrderServiceImpl;
import yoziming.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderManagerServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();



    // order 的顯示
    /**
     * 處理分頁功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 獲取請求的參數 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Integer userId = WebUtils.parseInt(req.getParameter("userId"), 0);

        // 2 調用OrderService.page(pageNo，pageSize)：Page物件
        Page<Order> page = orderService.page(userId, pageNo, pageSize);
        page.setUrl("orderServlet?action=page");

        // 3 保存Page物件到Request域中
        req.setAttribute("page",page);
        //4 請求轉發到pages/manager/book_manager.jsp頁面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        // 先獲取用戶
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 未登入
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        // 確認管理員權限(id 3以內)
        if (user.getUser_id() <= 3) {

            // 1 通過OrderService查詢全部圖書
            List<Order> orders = orderService.queryAllOrders();

            session.setAttribute("orders", orders);
            // 跳轉
            request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);

        } else {
            request.setAttribute("msg", "請使用管理員帳號登入");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }


    }



    // 顯示訂單詳情
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;


        // 1 獲取請求的參數：訂單號
        String orderId = req.getParameter("orderId");
        // 2 調用bookService.showOrderDetail 查詢訂單
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        // // 3 保存訂單列表到Request域中
        // req.setAttribute("orderItems", orderItems) ;

        HttpSession session = req.getSession();
        session.setAttribute("orderItems", orderItems);
        // //4 分頁
        // String url = req.getContextPath() + String.format("/orderServlet?action=page2&pageNo=%d&orderId=%s", pageNo,
        //         orderId);
        // resp.sendRedirect(url);

        // 跳轉
        resp.sendRedirect(req.getContextPath() + "/pages/manager/order_manager_detail.jsp?orderId=" + orderId);
    }


    /**
     * 管理員修改訂單為已發貨狀態
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
        // 確認管理員權限(id 3以內)
        if (user.getUser_id() <= 3) {
            String orderId = request.getParameter("orderId");
            orderService.sendOrder(orderId);
            // 跳轉
            response.sendRedirect(request.getContextPath() + "/manager/orderServlet?action=list");
        } else {
            request.setAttribute("msg", "請使用管理員帳號登入");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }
}
