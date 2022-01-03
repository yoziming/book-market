package com.yoziming.book.service;

import com.yoziming.book.pojo.Book;
import com.yoziming.book.pojo.Page;

import java.util.List;

public interface BookService {
    public int addBook(Book book);

    public int deleteBookById(int id);

    public int updateBook(Book book);

    public Book queryBookById(int id);

    public List<Book> queryBooks();

    public Page<Book> page(int pageNo, int pageSize);

    public Page<Book> pageByPrice(int pageNo, int pageSize ,int min, int max) ;

    }
