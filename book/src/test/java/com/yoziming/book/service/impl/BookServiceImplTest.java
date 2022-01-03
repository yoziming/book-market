package com.yoziming.book.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yoziming.book.pojo.User;
import com.yoziming.book.service.BookService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BookServiceImplTest {
    BookService bookService = new BookServiceImpl();

    @Test
    void page() {
        System.out.println(bookService.page(1, 4));

    }

    @Test
    public void test1() {
        User user = new User("zhung3", "pass123", "zhung3@goo.com");
        Gson gson = new Gson();

        String userString = gson.toJson(user);
        System.out.println(userString);

        User user2 = gson.fromJson(userString, User.class);
        System.out.println(user2);

        User user4 = new User("U4", "pass123", "zhung3@goo.com");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user4);

        // 把list轉成json
        String userListString = gson.toJson(userList);
        System.out.println(userListString);

        // 轉回
        List<User> fromJson = gson.fromJson(userListString, new TypeToken<List<User>>() {
        }.getType());
        System.out.println(userList);
        System.out.println(fromJson);

    }
}