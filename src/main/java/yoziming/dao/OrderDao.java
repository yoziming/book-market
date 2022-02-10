package yoziming.dao;

import yoziming.pojo.Order;
import yoziming.pojo.OrderItem;

import java.util.List;

public interface OrderDao {

    public int saveOrder(Order order);

    Integer queryForPageTotalCount(Integer userId);

    List<Order> queryForPageItems(Integer user_id, int begin, int pageSize);

    /**
     *  查詢某個用戶的所有訂單
     * @return 該用戶的所有訂單項
     */
    public List<Order> queryOrders(Integer user_id);

    /**
     *  查詢訂單狀況：已發貨或者未發貨
     * @param orderId
     * @param status
     * @return
     */
    public int changeOrderStatus(String orderId, int status);

    /**
     *  查看訂單詳情，其 OrderItem 中的信息
     * @param orderId 訂單 Id 號
     * @return
     */
    public OrderItem queryOrdersByOrderId(String orderId);

    List<Order> queryAllOrders();
}
