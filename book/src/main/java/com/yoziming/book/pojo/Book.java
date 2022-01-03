package com.yoziming.book.pojo;

import java.math.BigDecimal;

public class Book {
    private int id;
    private String name;
    private String author;
    private BigDecimal price;
    private int sales;
    private int stock;
    private String imgPath="static/img/default.jpg";

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public Book(String name, String author, BigDecimal price, int sales, int stock, String imgPath) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        // 如果用戶上傳的是空則不給賦，讓它顯示預設的圖片路徑
        if (imgPath!=null && !"".equals(imgPath)){
            this.imgPath = imgPath;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        if (imgPath!=null && !"".equals(imgPath)){
            this.imgPath = imgPath;
        }
    }
}
