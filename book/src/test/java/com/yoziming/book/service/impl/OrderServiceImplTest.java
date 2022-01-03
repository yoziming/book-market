package com.yoziming.book.service.impl;

import com.yoziming.book.pojo.Cart;
import com.yoziming.book.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        CartItem cartItem = new CartItem(1, "書本1", 5, new BigDecimal(50), new BigDecimal(250));
        LinkedHashMap<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
        map.put(1, cartItem);

        System.out.println(orderService.createOrder(new Cart(map), 1));
    }
}