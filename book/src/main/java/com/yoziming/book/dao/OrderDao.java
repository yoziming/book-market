package com.yoziming.book.dao;

import com.yoziming.book.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);

    /**
     * 返回所有訂單
     *
     * @return
     */
    List<Order> queryOrders();

    /**
     * 改變訂單狀態
     *
     * @param orderId
     * @param status
     * @return
     */
    int changeOrderStatus(String orderId, int status);

    /**
     * 返回用戶訂單
     *
     * @param userId
     * @return
     */
    List<Order> queryOrdersByUserId(int userId);

}
