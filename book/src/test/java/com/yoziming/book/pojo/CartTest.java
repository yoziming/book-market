package com.yoziming.book.pojo;

import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {
    Cart cart = new Cart();

    @Test
    public void addItem() {
        cart.addItem(new CartItem(1, "a", 3, new BigDecimal(10), new BigDecimal(30)));
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(10), new BigDecimal(30)));
        cart.addItem(new CartItem(2, "B", 10, new BigDecimal(100), new BigDecimal(1000)));
        cart.addItem(new CartItem(3, "C", 1, new BigDecimal(5), new BigDecimal(5)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1, "a", 3, new BigDecimal(10), new BigDecimal(30)));
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(10), new BigDecimal(30)));
        cart.addItem(new CartItem(2, "B", 10, new BigDecimal(100), new BigDecimal(1000)));
        cart.addItem(new CartItem(3, "C", 1, new BigDecimal(5), new BigDecimal(5)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1, "a", 3, new BigDecimal(10), new BigDecimal(30)));
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(10), new BigDecimal(30)));
        cart.addItem(new CartItem(2, "B", 10, new BigDecimal(100), new BigDecimal(1000)));
        cart.addItem(new CartItem(3, "C", 1, new BigDecimal(5), new BigDecimal(5)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1, "a", 1, new BigDecimal(10), new BigDecimal(10)));
        cart.updateCount(1, 10);
        System.out.println(cart);

    }
}