package yoziming.test;

import java.lang.reflect.Method;

public class UserServletTest {

    public void login() {
        System.out.println("這是login()方法調用了");
    }

    public void regist() {
        System.out.println("這是regist()方法調用了");
    }

    public void updateUser() {
        System.out.println("這是updateUser()方法調用了");
    }

    public void updateUserPassword() {
        System.out.println("這是updateUserPassword()方法調用了");
    }


    public static void main(String[] args) {
        String action = "regist";

        try {
            // 獲取action業務鑒別字符串，獲取相應的業務 方法反射物件
            Method method = UserServletTest.class.getDeclaredMethod(action);

//     System.out.println(method);
            // 調用目標業務 方法
            method.invoke(new UserServletTest() );

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
