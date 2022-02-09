package yoziming.dao;

import yoziming.pojo.User;

public interface UserDao {

    /**
     * 根據用戶名查詢用戶信息
     * @param username 用戶名
     * @return 如果返回null,說明沒有這個用戶。反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     * 根據 用戶名和密碼查詢用戶信息
     * @param username
     * @param password
     * @return 如果返回null,說明用戶名或密碼錯誤,反之亦然
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用戶信息
     * @param user
     * @return 返回-1表示操作失敗，其他是sql語句影響的行數
     */
    public int saveUser(User user);


}
