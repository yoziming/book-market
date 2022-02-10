<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <%--如果用戶還沒有登入，顯示     【登入 和註冊的菜單】 --%>
    <c:if test="${empty sessionScope.user}">
        <a href="pages/cart/cart.jsp">購物車</a>
        <a href="pages/user/login.jsp">登入</a> |
        <a href="pages/user/regist.jsp">註冊</a> &nbsp;&nbsp;
    </c:if>
    <%--如果已經登入，則顯示 登入 成功之後的用戶信息。--%>
    <c:if test="${not empty sessionScope.user}">
        <span>歡迎,<span class="um_span">${sessionScope.user.username}</span></span>
        <a href="pages/cart/cart.jsp">購物車</a>
        <a href="orderServlet?action=list">我的訂單</a>
        <a href="userServlet?action=logout">登出</a>&nbsp;&nbsp;&nbsp;
    </c:if>
    <a href="index.jsp">返回首頁</a>
</div>