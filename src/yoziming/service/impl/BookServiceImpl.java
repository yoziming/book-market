package yoziming.service.impl;

import yoziming.dao.BookDao;
import yoziming.dao.impl.BookDaoImpl;
import yoziming.pojo.Book;
import yoziming.pojo.Page;
import yoziming.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        // 設置每頁顯示的數量
        page.setPageSize(pageSize);
        // 求總記錄數
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 設置總記錄數
        page.setPageTotalCount(pageTotalCount);
        // 求總頁碼
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 設置總頁碼
        page.setPageTotal(pageTotal);

        // 設置當前頁碼
        page.setPageNo(pageNo);

        // 求當前頁數據的開始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求當前頁數據
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        // 設置當前頁數據
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        // 收到前端來的調用請求，準備一個Page物件來裝
        Page<Book> page = new Page<Book>();

        // 設置每頁顯示的數量
        page.setPageSize(pageSize);
        // 求總記錄數
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        // 設置總記錄數
        page.setPageTotalCount(pageTotalCount);
        // 求總頁碼
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            // 除不盡要加一頁，也包含不到一頁的情況
            pageTotal+=1;
        }
        // 設置總頁碼
        page.setPageTotal(pageTotal);
        // 設置當前頁碼
        page.setPageNo(pageNo);

        // 求當前頁數據的開始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求當前頁數據
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        // 設置當前頁數據
        page.setItems(items);

        return page;
    }
}
