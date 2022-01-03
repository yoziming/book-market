package com.yoziming.book.dao.impl;

import com.yoziming.book.dao.BookDao;
import com.yoziming.book.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`)" +
                "values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
                book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name` =?, `author`=? , `price`=? , `sales`=? , `stock`=? , `img_path`=? " +
                "where " +
                "id=?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
                book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book where id=?";
        return (Book) queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book";
        return (List<Book>) queryForList(Book.class, sql);
    }

    @Override
    public int queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        long l = (long) queryForSingleValue(sql);
        return new Long(l).intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql="select id, name, author, price, sales, stock, img_path imgPath from t_book limit ? , ?";
        return (List<Book>) queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public int queryForPageByPriceTotalCount(int min,int max) {
        String sql="select count(*) from t_book where price between ? and ?";
        long l = (long) queryForSingleValue(sql,min,max);
        return new Long(l).intValue();
    }

    @Override
    public List<Book> queryForPageByPriceItems(int begin, int pageSize, int min, int max) {
        String sql="select id, name, author, price, sales, stock, img_path imgPath from t_book " +
                "where price between ? and ? order by price limit ? , ?";
        return (List<Book>) queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
