<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<span>Hi,<span class="um_span">${sessionScope.user.username}</span></span>
<a href="OrderServlet?action=showMyOrders">我的訂單</a>
<a href="UserServlet?action=logout">登出</a>&nbsp;&nbsp;
<a href="">回首頁</a>
