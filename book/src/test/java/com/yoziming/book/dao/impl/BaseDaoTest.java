package com.yoziming.book.dao.impl;

import com.yoziming.book.pojo.Book;
import com.yoziming.book.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BaseDaoTest {
    private QueryRunner queryRunner=new QueryRunner();
    Connection conn = JdbcUtils.getConnection();

    @Test
    void t3() throws Exception {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book where id=?";
        Object query = queryRunner.query(conn, sql, new BeanHandler(Book.class), 1);
        System.out.println(query);
        Book query1 = (Book) query;
        System.out.println(query1);
    }


    @Test
    void update() {
    }

    @Test
    void queryForOne() {

    }

    @Test
    void queryForList() {
    }

    @Test
    void queryForSingleValue() {
    }
}