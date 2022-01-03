package com.yoziming.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    // 造一個ThreadLocal，裡面存的是Connection
    // 我預期讓同一個線程都用同一個conn來完成資料庫的事務操作
    // 因為事務要全部提交或全部回滾所以需要是同一個conn
    private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();

    // 讀取設定
    static {
        Properties properties = new Properties();
        try {
            properties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 獲取連接，返回null說明連接失敗
    public static Connection getConnection() {
        // 從threadLocal拿連接
        Connection conn = threadLocalConn.get();
        if (conn == null) {
            // 裡面還沒存東西呢
            try {
                // 就從連接池拿一個新的連線
                conn = dataSource.getConnection();
                // 並且存到線程域中，以後都調用它
                threadLocalConn.set(conn);
                // 設置手動提交準備搞事務
                conn.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事務並且關閉
     */
    public static void commitAndClose() {
        // 現在不需要把conn當作參數傳進來了，因為同一個線程我get就能取到它
        Connection conn = threadLocalConn.get();
        if (conn != null) {
            // 表示這個線程中有某dao取了連接並做了某些操作
            try {
                // 提交
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 銷毀線程中的conn防止記憶體洩漏，
            // 雖然GC內建會做這事，但手動可以增加效率
            // 且Tomcat使用了線程池，不手動remove會直接報錯
            threadLocalConn.remove();
        }
    }

    /**
     * 回滾事務並且關閉
     */
    public static void rollbackAndClose() {
        Connection conn = threadLocalConn.get();
        if (conn != null) {
            try {
                // 回滾
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            threadLocalConn.remove();
        }
    }

    // // 關閉連接
    // public static void close(Connection conn) {
    //     if (conn != null) {
    //         try {
    //             conn.close();
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //
    // }
}
