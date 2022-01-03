package com.yoziming.book.service.impl;

import com.yoziming.book.dao.UserDao;
import com.yoziming.book.dao.impl.UserDaoImpl;
import com.yoziming.book.pojo.User;
import com.yoziming.book.service.UserService;

public class UserServiceImpl implements UserService {

   private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassWord(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
