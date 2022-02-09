package yoziming.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 訂單
 */
public class Order {
    private String order_id;
    // 關於時間，插入到資料庫沒有問題，但是從資料庫讀取卻有問題
    private Date create_time;
    private BigDecimal price;
    // 0未發貨，1已發貨，2表示已簽收
    private Integer status = 0;
    private Integer user_id;

    public Order(String order_id, Date create_time, BigDecimal price, Integer status, Integer user_id) {
        this.order_id = order_id;
        this.create_time = create_time;
        this.price = price;
        this.status = status;
        this.user_id = user_id;
    }

    public Order() {
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", create_time=" + create_time +
                ", price=" + price +
                ", status=" + status +
                ", user_id=" + user_id +
                '}';
    }
}
