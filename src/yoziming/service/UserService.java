package yoziming.service;

import yoziming.pojo.User;

public interface UserService {

    /**
     * 註冊用戶
     * @param user
     */
    public void registUser(User user);

    /**
     * 登入
     * @param user
     * @return 如果返回null，說明登入失敗，返回有值，是登入成功
     */
    public User login(User user);

    /**
     * 檢查 用戶名是否可用
     * @param username
     * @return 返回true表示用戶名已存在，返回false表示用戶名可用
     */
    public boolean existsUsername(String username);
}
