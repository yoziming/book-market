package com.yoziming.book.service;

import com.yoziming.book.pojo.Cart;
import com.yoziming.book.pojo.Order;
import com.yoziming.book.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String createOrder(Cart cart, int userId);

    /**
     * 管理員查看所有訂單
     *
     * @return
     */
    List<Order> showAllOrders();

    /**
     * 管理員發貨
     *
     * @param orderId
     * @return
     */
    int sendOrder(String orderId);

    /**
     * 查看訂單詳情，顯示各項商品
     *
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 用戶查看自己的所有訂單
     *
     * @param userId
     * @return
     */
    List<Order> showMyOrders(int userId);

    /**
     * 用戶簽收訂單
     *
     * @param orderId
     * @return
     */
    int receiveOrder(String orderId);

}
