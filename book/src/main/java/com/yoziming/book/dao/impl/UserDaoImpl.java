package com.yoziming.book.dao.impl;

import com.yoziming.book.dao.UserDao;
import com.yoziming.book.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql="select * from t_user where username = ?";
        return (User) queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassWord(String username, String password) {
        String sql="select * from t_user where username = ? and password = ?";
        return (User) queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into t_user(username,password,email) values(?,?,?)";
                return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
