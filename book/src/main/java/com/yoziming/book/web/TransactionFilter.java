package com.yoziming.book.web;

import com.yoziming.book.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebFilter(filterName = "TransactionFilter", value = "/*")
public class TransactionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException
            , IOException {
        // 因為篩選條件是/*所有人都要來這關，當然也包含了所有事務
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        // 從請求頭獲取區域訊息
        Locale locale = request.getLocale();
/* 根據語言讀取語言包，包名是
i18n_en_US.properties
i18n_zh_TW.properties
裡面存的就是一行行鍵值對比如username=用戶名稱
 */
        ResourceBundle i18n = ResourceBundle.getBundle("i18n", locale);
        // 取出對應值顯示
        i18n.getString("username");
        try {
            chain.doFilter(request, response);
            // 在此提交事務
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            // 出錯就回滾，剛剛讓baseServlet手動拋出就是為了在這邊能接到
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }
}
