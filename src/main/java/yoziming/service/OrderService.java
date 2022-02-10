package yoziming.service;

import yoziming.pojo.Cart;
import yoziming.pojo.Order;
import yoziming.pojo.OrderItem;
import yoziming.pojo.Page;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    /**
     * 返回訂單的列表
     * @return
     */
    public List<Order> queryOrders(Integer userId);

    Page<Order> page(Integer userId, int pageNo, int pageSize);

    /**
     * 根據訂單號查詢該訂單的詳細信息，包括 name count 單價 總價
     * @param orderId
     * @return 某個訂單號的訂單列表
     */
    public List<OrderItem> showOrderDetail(String orderId);

    Page<OrderItem> page2(String orderId, int pageNo, int pageSize);

    void receiveOrder(String orderId);

    void sendOrder(String orderId);

    List<Order> queryAllOrders();
}
