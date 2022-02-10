package yoziming.service.impl;

import yoziming.dao.BookDao;
import yoziming.dao.OrderDao;
import yoziming.dao.OrderItemDao;
import yoziming.dao.impl.BookDaoImpl;
import yoziming.dao.impl.OrderDaoImpl;
import yoziming.dao.impl.OrderItemDaoImpl;
import yoziming.pojo.*;
import yoziming.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {

        System.out.println(" OrderServiceImpl 程序在[" +Thread.currentThread().getName() + "]中");

        // 訂單號===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        // 創建一個訂單物件
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
        // 保存訂單
        orderDao.saveOrder(order);

        // 遍歷購物車中每一個商品項轉換成為訂單項保存到資料庫
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 獲取每一個購物車中的商品項
            CartItem cartItem = entry.getValue();
            // 轉換為每一個訂單項
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            // 保存訂單項到資料庫
            orderItemDao.saveOrderItem(orderItem);

            // 更新庫存和銷量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getStock() - cartItem.getCount() );
            bookDao.updateBook(book);

        }
        // 清空購物車
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> queryOrders(Integer userId) {
        return orderDao.queryOrders(userId);
    }

    @Override
    public Page<Order> page(Integer userId, int pageNo, int pageSize) {
        Page<Order> page = new Page<Order>();

        // 設置每頁顯示的數量
        page.setPageSize(pageSize);
        // 求總記錄數
        Integer pageTotalCount = orderDao.queryForPageTotalCount(userId);
        // 設置總記錄數
        page.setPageTotalCount(pageTotalCount);
        // 求總頁碼
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 設置總頁碼
        page.setPageTotal(pageTotal);

        // 設置當前頁碼
        page.setPageNo(pageNo);

        // 求當前頁數據的開始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求當前頁數據
        List<Order> items = orderDao.queryForPageItems(userId, begin, pageSize);
        // 設置當前頁數據
        page.setItems(items);

        return page;
    }


    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return  orderItemDao.queryOrdersByOrderId(orderId);
    }

    @Override
    public Page<OrderItem> page2(String orderId, int pageNo, int pageSize) {
        Page<OrderItem> page2 = new Page<OrderItem>();

        // 設置每頁顯示的數量
        page2.setPageSize(pageSize);
        // 求總記錄數
        Integer pageTotalCount = orderItemDao.queryForPageTotalCount(orderId);
        // 設置總記錄數
        page2.setPageTotalCount(pageTotalCount);
        // 求總頁碼
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 設置總頁碼
        page2.setPageTotal(pageTotal);

        // 設置當前頁碼
        page2.setPageNo(pageNo);

        // 求當前頁數據的開始索引
        int begin = (page2.getPageNo() - 1) * pageSize;
        // 求當前頁數據
        List<OrderItem> items = orderItemDao.queryForPageItems(orderId, begin, pageSize);
        // 設置當前頁數據
        page2.setItems(items);

        return page2;
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 2);
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<Order> queryAllOrders() {
        return  orderDao.queryAllOrders();

    }
}
