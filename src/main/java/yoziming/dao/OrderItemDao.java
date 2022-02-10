package yoziming.dao;

import yoziming.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);

    Integer queryForPageTotalCount(String orderId);

    List<OrderItem> queryForPageItems(String orderId, int begin, int pageSize);

    /**
     *  查看訂單詳情，其 OrderItem 中的信息
     * @param orderId 訂單 Id 號
     * @return
     */
    public List<OrderItem> queryOrdersByOrderId(String orderId);
}
