package com.yoziming.book.dao.impl;

import com.yoziming.book.dao.OrderItemDao;
import com.yoziming.book.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item (name, count, price, total_price , order_id )" +
                "values (?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(),
                orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select id, name, count, price, total_price totalPrice, order_id orderId from t_order_item where" +
                " " +
                "order_id=?";
        return (List<OrderItem>) queryForList(OrderItem.class, sql, orderId);
    }
}
