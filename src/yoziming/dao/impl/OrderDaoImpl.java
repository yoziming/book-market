package yoziming.dao.impl;

import yoziming.dao.OrderDao;
import yoziming.pojo.Order;
import yoziming.pojo.OrderItem;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {

        System.out.println(" OrderDaoImpl 程序在[" +Thread.currentThread().getName() + "]中");

        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        // 原來 sql 語句中的問號針對的不是 `` 中的變量，而是下面這個語句中的變量
        return update(sql,order.getOrder_id(),order.getCreate_time(),order.getPrice(),order.getStatus(),order.getUser_id());
    }

    @Override
    public List<Order> queryOrders(Integer userId) {
        // 無框架時如何配置資料庫帶有下劃線字段對應Java實體類屬性
        String sql = "select  `order_id`, `create_time` , `price` , `status` ,`user_id` from t_order where `user_id` " +
                "= ?";
        return queryForList(Order.class, sql, userId);
    }

    @Override
    public Integer queryForPageTotalCount(Integer userId) {
        String sql = "select count(*) from t_order where `user_id` = ?";
        Number count = (Number) queryForSingleValue(sql, userId);
        return count.intValue();
    }

    @Override
    public List<Order> queryForPageItems(Integer user_id, int begin, int pageSize) {
        String sql = "select `order_id`, `create_time` , `price` , `status`  from t_order where `user_id` =" +
                " ? limit ?,? ";
        return queryForList(Order.class, sql, user_id, begin, pageSize);
    }


    @Override
    public int changeOrderStatus(String orderId, int status) {
        String sql = "update t_order set `status`=? where `order_id`=? ";
        return update(sql, status, orderId);
    }

    @Override
    public OrderItem queryOrdersByOrderId(String orderId) {
        return null;
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "select order_id, create_time, price, status, user_id from t_order";
        return (List<Order>) queryForList(Order.class, sql);
    }
}
