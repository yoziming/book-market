package com.yoziming.book.service.impl;

import com.yoziming.book.dao.BookDao;
import com.yoziming.book.dao.OrderDao;
import com.yoziming.book.dao.OrderItemDao;
import com.yoziming.book.dao.impl.BookDaoImpl;
import com.yoziming.book.dao.impl.OrderDaoImpl;
import com.yoziming.book.dao.impl.OrderItemDaoImpl;
import com.yoziming.book.pojo.*;
import com.yoziming.book.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, int userId) {
        // 新創訂單，保存到資料庫
        // 唯一的訂單號
        String orderId = System.currentTimeMillis() + "U" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
        // 拿出購物車的商品項
        for (CartItem item : cart.getItems().values()) {
            OrderItem orderItem = new OrderItem(item.getName(), item.getCount(), item.getPrice(),
                    item.getTotalPrice(), orderId);
            // 對資料庫中書本數量修改
            Book book = bookDao.queryBookById(item.getId());
            book.setSales(book.getSales() + item.getCount());
            book.setStock(book.getStock() - item.getCount());
            bookDao.updateBook(book);

            // 保存訂單商品項到資料庫
            orderItemDao.saveOrderItem(orderItem);

        }
        // 清空購物車
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public int sendOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(int userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public int receiveOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 2);
    }
}
