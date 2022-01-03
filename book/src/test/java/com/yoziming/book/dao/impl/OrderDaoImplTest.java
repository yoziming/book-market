package com.yoziming.book.dao.impl;

import com.yoziming.book.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDaoImplTest {
    OrderDaoImpl orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        Order order = new Order("123", new Date(), new BigDecimal(100), 0, 1);

        int i = orderDao.saveOrder(order);
        System.out.println(i);
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("1641104487686U14", 1);
    }

    @Test
    public void queryOrdersByUserId() {
        for (Order order : orderDao.queryOrdersByUserId(14)) {
            System.out.println(order);
        }
    }
}

/*
cannot convert java.time.LocalDateTime to java.util.Date

把mysql-connector-java8.0.26換成8.0.21版，問題消失，
26版會將datatime轉換成java.time.LocalDateTime
而21版轉換成java.util.Date
 */