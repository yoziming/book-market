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

public class ClientBookServlet extends BaseServlet {

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
        page.setUrl("client/bookServlet?action=page");
        // 3 保存Page物件到Request域中
        req.setAttribute("page",page);
        //4 請求轉發到pages/manager/book_manager.jsp頁面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
    /**
     * 處理分頁功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 獲取請求的參數 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        // 防止亂填
        if (max<0){
            max = 9999;
        }
        if (min > max) {
            min = 0;
        }


        // 2 調用BookService.page(pageNo，pageSize)：Page物件
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小價格的參數,追加到分頁條的地址參數中
        if (req.getParameter("min") != null) {
            // sb.append("&min=").append(req.getParameter("min"));
            sb.append("&min=").append(min);
        }
        // 如果有最大價格的參數,追加到分頁條的地址參數中
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(9999);
        }
        page.setUrl(sb.toString());
        // 3 保存Page物件到Request域中
        req.setAttribute("page",page);
        //4 請求轉發到pages/manager/book_manager.jsp頁面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}
