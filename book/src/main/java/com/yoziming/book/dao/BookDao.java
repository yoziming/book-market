package com.yoziming.book.dao;

import com.yoziming.book.pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(int id);
    public int updateBook(Book book);
    public Book queryBookById(int id);
    public List<Book> queryBooks();

    int queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    int queryForPageByPriceTotalCount(int min,int max);

    List<Book> queryForPageByPriceItems(int begin, int pageSize,int min,int max);
}
