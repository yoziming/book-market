package yoziming.test;

import org.junit.Test;
import yoziming.dao.OrderItemDao;
import yoziming.dao.impl.OrderItemDaoImpl;
import yoziming.pojo.OrderItem;
import yoziming.pojo.Page;

import java.math.BigDecimal;

public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {

        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        // 不能直接為該表增加數據了，因為該表的 orderId 與另一張表相關聯，如果該表變，那另外一張就會有影響。
        orderItemDao.saveOrderItem(new OrderItem(null,"java從入門到精通", 1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript從入門到精通", 2,new BigDecimal(100),new BigDecimal(200),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty入門", 1,new BigDecimal(100),new BigDecimal(100),"1234567890"));

    }

    @Test
    public void queryOrdersByOrderId() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        // Java 的String 類型找不到資料庫的varchar類型。可以找到。
// orderItemDao.queryOrdersByOrderId("16158785947351");
        for (OrderItem book : orderItemDao.queryOrdersByOrderId("16158960475011")) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageItems() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        for (OrderItem orderItem : orderItemDao.queryForPageItems("16159466449511", 1, Page.PAGE_SIZE)) {
            System.out.println(orderItem);
        }
    }
}