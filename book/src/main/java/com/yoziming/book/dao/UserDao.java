package com.yoziming.book.dao;

import com.yoziming.book.pojo.User;

public interface UserDao {
    // 根據用戶名返回用戶訊息
    public User queryUserByUsername(String username);

    // 驗證帳密登入
    public User queryUserByUsernameAndPassWord(String username,String password);

    // 保存用戶訊息
    public int saveUser(User user);

}
