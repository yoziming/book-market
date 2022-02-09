package yoziming.test;

import yoziming.pojo.Cart;
import yoziming.pojo.CartItem;
import yoziming.pojo.Order;
import yoziming.pojo.OrderItem;
import yoziming.service.OrderService;
import yoziming.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {

    OrderService orderService= new OrderServiceImpl();

    @Test
    public void createOrder() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java從入門到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java從入門到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "數據結構與算法", 1, new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println( "訂單號是：" + orderService.createOrder(cart, 1) );

    }

    @Test
    public void queryOrders() {
        for (Order orders : orderService.queryOrders(1)) {
            System.out.println(orders);
        }
    }

    @Test
    public void showOrderDetail(){
        for (OrderItem orderItem : orderService.showOrderDetail("16155333252741")) {
            System.out.println(orderItem);
        }
    }

}