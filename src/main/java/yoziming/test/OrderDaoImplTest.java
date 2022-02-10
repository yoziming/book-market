package yoziming.test;

import org.junit.Test;
import yoziming.dao.OrderDao;
import yoziming.dao.impl.OrderDaoImpl;
import yoziming.pojo.Order;

public class OrderDaoImplTest {

    @Test
    public void queryOrders() {
        OrderDao order = new OrderDaoImpl();
        // Java 的String 類型找不到資料庫的varchar類型。可以找到。
// orderItemDao.queryOrdersByOrderId("16158785947351");
        // 原來一定要遍歷輸出
        for (Order book : order.queryOrders(1)) {
            System.out.println(book);
        }
    }
}