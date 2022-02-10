package yoziming.filter;

import yoziming.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
          
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose();// 提交事務
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();//回滾事務
            e.printStackTrace();
            throw new RuntimeException(e);//把異常拋給Tomcat管理展示友好的錯誤頁面
        }
    }

    @Override
    public void destroy() {

    }
}
