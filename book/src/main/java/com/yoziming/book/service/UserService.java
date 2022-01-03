package com.yoziming.book.service;

import com.yoziming.book.pojo.User;

public interface UserService {
    /**
     * 註冊
     * @param user
     */
    public void registUser(User user);

    /**
     * 登入
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 檢查用戶名是否已存在
     * @param username
     * @return true=已存在，不可註冊
     */
    public boolean existsUsername(String username);
}
