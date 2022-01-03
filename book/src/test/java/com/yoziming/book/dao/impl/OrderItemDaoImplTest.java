package com.yoziming.book.dao.impl;

import com.yoziming.book.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemDaoImplTest {
    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {

        System.out.println(orderItemDao.saveOrderItem(new OrderItem("aaa", 5, new BigDecimal(50), new BigDecimal(250)
                , "123")));

    }

    @Test
    public void queryOrderItemsByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId("1641104487686U14");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}