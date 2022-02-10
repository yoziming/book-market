package yoziming.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private static final String HTTPS = "https";
    private static final int HTTPS_PORT = 8443;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        URL newUrl = null;
        if (request.getScheme().equals(HTTPS)) {
            chain.doFilter(request, response);
        } else {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String queryString = httpRequest.getQueryString() == null ? "" : "?" + httpRequest.getQueryString();
            httpResponse.setStatus(307);
            String requestUrl = httpRequest.getRequestURL().toString();
            URL reqUrl = new URL(requestUrl + queryString);
            newUrl = new URL(HTTPS, reqUrl.getHost(), HTTPS_PORT, reqUrl.getFile());
            //進行重定向
            httpResponse.setHeader("Location", newUrl.toString());
            httpResponse.setHeader("Connection", "close");
            //允許所有跨域請求
            httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        }
    }

    @Override
    public void destroy() {
    }
}