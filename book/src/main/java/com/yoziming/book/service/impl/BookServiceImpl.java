package com.yoziming.book.service.impl;

import com.yoziming.book.dao.BookDao;
import com.yoziming.book.dao.impl.BookDaoImpl;
import com.yoziming.book.pojo.Book;
import com.yoziming.book.pojo.Page;
import com.yoziming.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBookById(int id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
public Page<Book> page(int pageNo, int pageSize) {
    // 收到前端來的調用請求，準備一個Page物件來裝
    Page<Book> page = new Page<Book>();
    page.setPageSize(pageSize);

    // 先看資料庫總共有幾筆資料，算共會裝成幾頁
    int pageTotalCount = bookDao.queryForPageTotalCount();
    page.setPageTotalCount(pageTotalCount);

    // 求總頁碼
    int pageTotal = pageTotalCount / pageSize;
    if (pageTotalCount % pageSize > 0) {
        // 除不盡要加一頁，也包含不到一頁的情況
        pageTotal += 1;
    }
    page.setPageTotal(pageTotal);

    page.setPageNo(pageNo);
    // 當前頁面的資料
    int begin = (page.getPageNo() - 1) * pageSize;
    List<Book> items = bookDao.queryForPageItems(begin, pageSize);
    // 封裝
    page.setItems(items);

    return page;
}

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize ,int min, int max) {
        // 收到前端來的調用請求，準備一個Page物件來裝
        Page<Book> page = new Page<Book>();
        page.setPageSize(pageSize);

        // 先看資料庫總共有幾筆資料，算共會裝成幾頁
        int pageTotalCount = bookDao.queryForPageByPriceTotalCount(min,max);
        page.setPageTotalCount(pageTotalCount);

        // 求總頁碼
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            // 除不盡要加一頁，也包含不到一頁的情況
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);
        // 當前頁面的資料
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageByPriceItems(begin, pageSize,min,max);
        // 封裝
        page.setItems(items);

        return page;
    }

}
