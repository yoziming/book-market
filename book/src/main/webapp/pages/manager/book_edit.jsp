<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>編輯圖書</title>
    <%-- 靜態包含 base標簽、css樣式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">編輯圖書</span>
    <%-- 靜態包含 manager管理模塊的菜單  --%>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>
<div id="main">
    <form action="BookServlet" method="post">
        <input type="hidden" name="action" value="${empty param.id?"add":"update"}"/>
        <input type="hidden" name="id" value="${requestScope.book.id}"/>
        <input type="hidden" name="pageNo" value="${param.pageNo}"/>
        <table>
            <tr>
                <td>名稱</td>
                <td>價格</td>
                <td>作者</td>
                <td>銷量</td>
                <td>庫存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>