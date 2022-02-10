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

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成訂單
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先獲取Cart購物車物件
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 獲取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        System.out.println("OrderServlet程序在[" +Thread.currentThread().getName() + "]中");

        Integer userId = loginUser.getUser_id();
        // 調用orderService.createOrder(Cart,Userid);生成訂單
        String orderId = orderService.createOrder(cart, userId);
        // req.setAttribute("orderId", orderId);
        // 請求轉發到/pages/cart/checkout.jsp
        // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

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

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        User loginUser = (User) req.getSession().getAttribute("user");
        Integer userId = loginUser.getUser_id();

        // 1 通過OrderService查詢全部圖書
        List<Order> orders = orderService.queryOrders(userId);
        // 當該用戶的訂單數量為 0 時，提示用戶自己現在還沒有訂單，並且轉到首頁購物
        if(orders.size() == 0){
            req.getRequestDispatcher("/pages/order/emptyOrder.jsp").forward(req,resp);
            // 如果不加上 return 強制跳出該方法，代碼仍然會執行下去，從而導致程序拋出異常。
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("orders", orders);
        // 跳轉
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);

        // // 2 把全部訂單保存到Request域中
        // req.setAttribute("orders", orders);
        // // 3 分頁
        // String url = req.getContextPath() + String.format("/orderServlet?action=page&pageNo=%d&userId=%d", pageNo,
        //         userId);
        //
        // resp.sendRedirect(url);
    }



    // orderItem 的顯示
    /**
     * 處理分頁功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 獲取請求的參數 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        String orderId = req.getParameter("orderId");

        // 2 調用OrderService.page(pageNo，pageSize)：Page物件
        Page<OrderItem> page2 = orderService.page2(orderId, pageNo, pageSize);
        page2.setUrl("orderServlet?action=page2");

        // 3 保存Page物件到Request域中
        req.setAttribute("page2",page2);
        //4 請求轉發到pages/manager/book_manager.jsp頁面
        req.getRequestDispatcher("/pages/order/orderItem.jsp").forward(req,resp);
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
        resp.sendRedirect(req.getContextPath() + "/pages/order/order_detail.jsp?orderId=" + orderId);
    }

    /**
     * 訂單收貨
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();

        String orderId = request.getParameter("orderId");
        orderService.receiveOrder(orderId);
        // 跳轉
        response.sendRedirect(request.getContextPath() + "/orderServlet?action=list");

    }


}
