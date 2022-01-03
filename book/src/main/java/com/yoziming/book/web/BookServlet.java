package com.yoziming.book.web;

import com.yoziming.book.pojo.Book;
import com.yoziming.book.pojo.Page;
import com.yoziming.book.service.BookService;
import com.yoziming.book.service.impl.BookServiceImpl;
import com.yoziming.book.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 封裝成book物件
        Book book = new Book();
        BeanUtils.populate(book, request.getParameterMap());

        // 存到資料庫
        bookService.addBook(book);
        // 轉發到另一個請求
        // 讓頁面+1，保證永遠都在最後一頁
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
        pageNo+=1;
        response.sendRedirect(request.getContextPath() + "/BookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        bookService.deleteBookById(Integer.parseInt(request.getParameter("id")));

        response.sendRedirect(request.getContextPath() + "/BookServlet?action=page&pageNo="+request.getParameter(
                "pageNo"));

    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 修改的第一步，獲取圖書資訊
        Book book = bookService.queryBookById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("book", book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 封裝成book物件
        Book book = new Book();
        BeanUtils.populate(book, request.getParameterMap());
        // 存到資料庫
        bookService.updateBook(book);
        // 轉發到另一個請求
        response.sendRedirect(request.getContextPath() + "/BookServlet?action=page&pageNo="+request.getParameter(
                "pageNo"));

    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 查詢全部圖書
        List<Book> books = bookService.queryBooks();
        // 保存到req域
        request.setAttribute("books", books);
        // 轉發
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

    }

protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException,
        IOException {
    int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
    int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
    Page<Book> page = bookService.page(pageNo, pageSize);
    page.setUrl("BookServlet?action=page");
    request.setAttribute("page", page);
    request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

}

}
