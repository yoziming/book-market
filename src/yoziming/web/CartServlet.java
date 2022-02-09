package yoziming.web;

import yoziming.pojo.Book;
import yoziming.pojo.Cart;
import yoziming.pojo.CartItem;
import yoziming.service.BookService;
import yoziming.service.impl.BookServiceImpl;
import yoziming.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 修改商品數量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 獲取請求的參數 商品編號 、商品數量
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // 獲取Cart購物車物件
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // 修改商品數量
            cart.updateCount(id,count);
            // 重定向回原來購物車展示頁面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空購物車
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1 獲取購物車物件
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 清空購物車
            cart.clear();
            // 重定向回原來購物車展示頁面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    /**
     * 刪除商品項
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 獲取商品編號
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 獲取購物車物件
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // 刪除 了購物車商品項
            cart.deleteItem(id);
            // 重定向回原來購物車展示頁面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }



    /**
     * 加入購物車
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 獲取請求的參數 商品編號
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 調用bookService.queryBookById(id):Book得到圖書的信息
        Book book = bookService.queryBookById(id);
        // 把圖書信息，轉換成為CartItem商品項
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 調用Cart.addItem(CartItem);添加商品項
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println("請求頭Referer的值：" + req.getHeader("Referer"));
        // 最後一個添加的商品名稱
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 重定向回原來商品所在的地址頁面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 獲取請求的參數 商品編號
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 調用bookService.queryBookById(id):Book得到圖書的信息
        Book book = bookService.queryBookById(id);
        // 把圖書信息，轉換成為CartItem商品項
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 調用Cart.addItem(CartItem);添加商品項
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        // 最後一個添加的商品名稱
        req.getSession().setAttribute("lastName", cartItem.getName());

        //6、返回購物車總的商品數量和最後一個添加的商品名稱
        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJsonString);

    }





}
