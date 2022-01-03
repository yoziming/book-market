package com.yoziming.book.web;

import com.google.gson.Gson;
import com.yoziming.book.pojo.Book;
import com.yoziming.book.pojo.Cart;
import com.yoziming.book.pojo.CartItem;
import com.yoziming.book.service.BookService;
import com.yoziming.book.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 先進session看看是否已有車
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            // 造一個新購物車物件，準備用來裝
            cart = new Cart();
        }
        // 已經有車
        // 從請求參數獲取書的ID
        String bookId = request.getParameter("bookId");
        // 用id找到書的資訊
        Book book = bookService.queryBookById(Integer.parseInt(bookId));
        // 轉換成cartItem物件
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 把cartItem物件進車
        cart.addItem(cartItem);

        // 整台車裝進session
        session.setAttribute("cart", cart);
        // 最後新增的書
        session.setAttribute("lastAddBook", book.getName());
        // 轉址回原先頁
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 先進session看看是否已有車
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            // 造一個新購物車物件，準備用來裝
            cart = new Cart();
        }
        // 已經有車
        // 從請求參數獲取書的ID
        String bookId = request.getParameter("bookId");
        // 用id找到書的資訊
        Book book = bookService.queryBookById(Integer.parseInt(bookId));
        // 轉換成cartItem物件
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 把cartItem物件進車
        cart.addItem(cartItem);

        // 造一個map存結果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("lastAddBook", book.getName());
        resultMap.put("totalPrice", cart.getTotalPrice());
        resultMap.put("totalCount", cart.getTotalCount());

        // 格式化並返回
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 先進session看看是否已有車
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            // 從請求參數獲取書的ID
            String bookId = request.getParameter("bookId");
            cart.deleteItem(Integer.parseInt(bookId));
            // 整台車裝進session
            session.setAttribute("cart", cart);
        }
        // 轉址回原先頁
        response.sendRedirect(request.getHeader("Referer"));

    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 先進session看看是否已有車
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.clear();
            // 整台車裝進session
            session.setAttribute("cart", cart);
        }
        // 轉址回原先頁
        response.sendRedirect(request.getHeader("Referer"));

    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 先進session看看是否已有車
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            // 從請求參數獲取書的ID
            String bookId = request.getParameter("bookId");
            String bookCount = request.getParameter("bookCount");
            cart.updateCount(Integer.parseInt(bookId), Integer.parseInt(bookCount));

            // 整台車裝進session
            session.setAttribute("cart", cart);

        }
        // 轉址回原先頁
        response.sendRedirect(request.getHeader("Referer"));

    }
}
