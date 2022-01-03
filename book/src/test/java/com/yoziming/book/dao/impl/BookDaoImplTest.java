package com.yoziming.book.dao.impl;

import com.yoziming.book.dao.BookDao;
import com.yoziming.book.pojo.Book;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoImplTest {
private BookDao bookDao =new BookDaoImpl();

    @Test
    void addBook() {
        bookDao.addBook(new Book("aaa","bbb",new BigDecimal("99.2"),50,2,"gg/aa"));

    }

    @Test
    void deleteBookById() {
        bookDao.deleteBookById(22);

    }

    @Test
    void updateBook() {
        Book book2 = bookDao.queryBookById(23);
        book2.setImgPath("222222222");
        bookDao.updateBook(book2);

    }

    @Test
    void queryBookById() {
        Book book = bookDao.queryBookById(22);
        System.out.println(book);
    }

    @Test
    void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        books.forEach(System.out::println);
    }

    @Test
    void queryForPageTotalCount() {
        int count = bookDao.queryForPageTotalCount();
        System.out.println(count);
    }

    @Test
    void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(0, 5);
        books.forEach(System.out::println);
    }
}