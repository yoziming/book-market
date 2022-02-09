package yoziming.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            // 讀取 jdbc.properties屬性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 從流中加載數據
            properties.load(inputStream);
            // 創建 資料庫連接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 獲取資料庫連接池中的連接
     * @return 如果返回null,說明獲取連接失敗<br/>有值就是獲取連接成功
     */
    public static Connection getConnection(){
        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();//從資料庫連接池中獲取連接
                conns.set(conn); // 保存到ThreadLocal物件中，供後面的jdbc操作使用
                conn.setAutoCommit(false); // 設置為手動管理事務
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事務，並關閉釋放連接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null) { // 如果不等於null，說明 之前使用過連接，操作過資料庫
            try {
                connection.commit(); // 提交 事務
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 關閉連接，資源資源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要執行remove操作，否則就會出錯。（因為Tomcat服務器底層使用了線程池技術）
        conns.remove();
    }

    /**
     * 回滾事務，並關閉釋放連接
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null) { // 如果不等於null，說明 之前使用過連接，操作過資料庫
            try {
                connection.rollback();//回滾事務
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 關閉連接，資源資源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要執行remove操作，否則就會出錯。（因為Tomcat服務器底層使用了線程池技術）
        conns.remove();
    }


    /**
     * 關閉連接，放回資料庫連接池
     * @param conn

    public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } */

}