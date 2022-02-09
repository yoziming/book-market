package yoziming.web;

import yoziming.pojo.Book;
import yoziming.pojo.Page;
import yoziming.service.BookService;
import yoziming.service.impl.BookServiceImpl;
import yoziming.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     * 處理分頁功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 獲取請求的參數 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2 調用BookService.page(pageNo，pageSize)：Page物件
        Page<Book> page = bookService.page(pageNo,pageSize);

        page.setUrl("manager/bookServlet?action=page");

        // 3 保存Page物件到Request域中
        req.setAttribute("page",page);
        //4 請求轉發到pages/manager/book_manager.jsp頁面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }



    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        // 1、獲取請求的參數==封裝成為Book物件
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        // 2、調用BookService.addBook()保存圖書
        bookService.addBook(book);
        // 3、跳到圖書列表頁面
        //         /manager/bookServlet?action=list
        // req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }



    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、獲取請求的參數id，圖書編程
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2、調用bookService.deleteBookById();刪除圖書
        bookService.deleteBookById(id);
        // 3、重定向回圖書列表管理頁面
        //         /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }



    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、獲取請求的參數==封裝成為Book物件
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        // 2、調用BookService.updateBook( book );修改圖書
        bookService.updateBook(book);
        // 3、重定向回圖書列表管理頁面
        // 地址：/工程名/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 獲取請求的參數圖書編號
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2 調用bookService.queryBookById查詢圖書
        Book book = bookService.queryBookById(id);
        // 3 保存到圖書到Request域中
        req.setAttribute("book", book) ;
        //4 請求轉發到 pages/manager/book_edit.jsp頁面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }



    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        // 1 通過BookService查詢全部圖書
        List<Book> books = bookService.queryBooks();
        // 2 把全部圖書保存到Request域中
        req.setAttribute("books", books);
        // 3、請求轉發到/pages/manager/book_manager.jsp頁面
        // req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }




}
